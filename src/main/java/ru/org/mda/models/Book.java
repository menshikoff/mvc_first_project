package ru.org.mda.models;

import java.util.Optional;

public class Book {

    private int bookid;
    private Integer personid;
    private String name;
    private String author;
    private int year;


    public Book(int bookid, Integer personid, String name, String author, int year) {
        this.bookid = bookid;
        this.personid = personid;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {this.bookid = bookid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
