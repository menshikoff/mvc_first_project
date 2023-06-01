package ru.org.mda.models;

public class Person {

    private int personid;
    private String name;
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
