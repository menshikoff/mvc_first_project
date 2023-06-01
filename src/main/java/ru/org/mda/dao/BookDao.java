package ru.org.mda.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.org.mda.models.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Books(name, author, year) VALUES (?, ?, ?)",
                                book.getName(), book.getAuthor(), book.getYear());
    }

    public Book show(int bookid) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE bookid=?",
                                        new BeanPropertyRowMapper<>(Book.class), bookid).stream().findAny().orElse(null);
    }

    public void update(int bookid, Book updatedBook) {
        jdbcTemplate.update("UPDATE Books SET name=?, author=?, year=? WHERE bookid=?",
                updatedBook.getName(),
                updatedBook.getAuthor(),
                updatedBook.getYear(),
                bookid
        );
    }

    public void delete(int bookid) {
        jdbcTemplate.update("DELETE FROM Books WHERE bookid=?", bookid);
    }

    public void assignBook(int bookid, int personid) {
        jdbcTemplate.update("UPDATE Books SET personid=? WHERE bookid=?", personid, bookid);
    }

    public void unssignedBook(int bookid) {
        jdbcTemplate.update("UPDATE Books SET personid=null WHERE bookid=?", bookid);
    }


}
