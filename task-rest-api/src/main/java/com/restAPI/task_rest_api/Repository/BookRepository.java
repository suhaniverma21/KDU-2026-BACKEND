
package com.restAPI.task_rest_api.Repository;

import org.springframework.stereotype.Repository;
import java.util.*;
import static java.util.Optional.ofNullable;

import com.restAPI.task_rest_api.Model.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;
@Repository
public class BookRepository {
    private final Map<Long, Book> bookStore = new HashMap<>();
    private Integer idCounter = 1;

    public Book save(Book book) {
        book.setId(Long.valueOf(idCounter++));
        bookStore.put(book.getId(), book);
        return book;
    }

    //Optional<Book> coz there may or may not be a book
    public Optional<Book> findById(Long id) {
        return ofNullable(bookStore.get(id));
    }
    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }
    public Book update(Long id,Book book) {
        //book.setId(Long.valueOf(idCounter++));
        bookStore.put(id, book);
        return book;
    }
    public void deleteById(Long id)
    {
        bookStore.remove(id);
    }
}
