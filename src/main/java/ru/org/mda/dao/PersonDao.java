package ru.org.mda.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.org.mda.models.Book;
import ru.org.mda.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year) VALUES (?, ?)", person.getName(), person.getYear());
    }

    public Person show(int personid) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personid=?",
                        new BeanPropertyRowMapper<>(Person.class), personid).stream()
                                .findAny().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new BeanPropertyRowMapper<>(Person.class),
                new Object[] {name}).stream().findAny();
    }

    public void update(int personid, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name =?, year =? WHERE personid = ?",
                updatedPerson.getName(), updatedPerson.getYear(), personid);
    }

    public void deletePerson(int personid){
        jdbcTemplate.update("DELETE FROM Person WHERE personid=?", personid);
    }

    public List<Book> personBooks(int personid) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE personid=?",
                new BeanPropertyRowMapper<>(Book.class), personid);
    }

}
