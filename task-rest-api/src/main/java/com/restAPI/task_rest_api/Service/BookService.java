package com.restAPI.task_rest_api.Service;



import com.restAPI.task_rest_api.Model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addBook(Book book);

    Optional<Book> getBookById(Long id);

    List<Book> getAllBooks();

    //ArrayList<Book> getBookByAuthor(String name);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);

}

