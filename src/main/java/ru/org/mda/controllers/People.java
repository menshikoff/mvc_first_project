package ru.org.mda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.org.mda.dao.PersonDao;
import ru.org.mda.models.Person;
import ru.org.mda.util.PersonValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("people")
public class People {

    private PersonDao personDao;
    private PersonValidator personValidator;

    @Autowired
    public People(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("/{personid}")
    public String show(@PathVariable("personid") int personid, Model model) {
        model.addAttribute("person", personDao.show(personid));
        model.addAttribute("books", personDao.personBooks(personid));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDao.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{personid}/edit")
    public String edit(@PathVariable("personid") int personid, Model model)
    {
        model.addAttribute("person", personDao.show(personid));

        return "people/edit";
    }

    @PatchMapping("/{personid}")
    public String update(@PathVariable("personid") int personid,
                         @ModelAttribute("person") Person person)
    {
        personDao.update(personid, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{personid}")
    public String deletePerson(@PathVariable("personid") int persoid){
        personDao.deletePerson(persoid);
        return "redirect:/people";
    }

}
