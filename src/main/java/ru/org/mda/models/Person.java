package ru.org.mda.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int personid;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле имя не может быть меньше 2х и больше 100 символов")
    private String name;
    @Min(value=1901, message = "Год рождения не может быть меньше 1901")
    private int year;

    public Person(int personid, String name, int year) {
        this.personid = personid;
        this.name = name;
        this.year = year;
    }

    public Person() {}

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
