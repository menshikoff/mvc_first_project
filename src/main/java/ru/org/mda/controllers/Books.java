package ru.org.mda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.org.mda.dao.BookDao;
import ru.org.mda.dao.PersonDao;
import ru.org.mda.models.Book;
import ru.org.mda.models.Person;

@Controller
@RequestMapping("/books")
public class Books {

    private BookDao bookDao;
    private PersonDao personDao;

    @Autowired
    public Books(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "/books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping
    public String saveBook(@ModelAttribute("book") Book book) {
        bookDao.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{bookid}")
    public String show(@PathVariable("bookid") int bookid, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDao.show(bookid));
        model.addAttribute("people", personDao.index());
        return "books/show";
    }

    @GetMapping("/{bookid}/edit")
    public String editBook(@PathVariable("bookid") int bookid, Model model) {

        model.addAttribute("book", bookDao.show(bookid));
        return "books/edit";
    }

    @PatchMapping("/{bookid}")
    public String updateBook(@PathVariable("bookid") int bookid,
                             @ModelAttribute("book") Book updatedBook) {
        bookDao.update(bookid, updatedBook);
        return "redirect:/books";
    }

    @DeleteMapping("/{bookid}")
    public String deleteBook(@PathVariable("bookid") int bookid) {
        bookDao.delete(bookid);
        return "redirect:/books";
    }

    @PatchMapping("/assign/{bookid}")
    public String assignBook(@PathVariable("bookid") int bookid,
                             @ModelAttribute("person") Person person) {
            bookDao.assignBook(bookid, person.getPersonid());
        return "redirect:/books";
    }

    @PatchMapping("/unassigned/{bookid}")
    public String unassignedBook(@PathVariable("bookid") int bookid) {
            bookDao.unssignedBook(bookid);
        return "redirect:/books";
    }


}
