package ru.org.mda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.org.mda.dao.PersonDao;
import ru.org.mda.models.Person;

@Component
public class PersonValidator implements Validator {

    private PersonDao personDao;

    @Autowired
    public PersonValidator(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDao.show(person.getName()).isPresent())
            errors.rejectValue("name", "", "Пользователь с данным именем уже зарегистрирован!");

    }
}
