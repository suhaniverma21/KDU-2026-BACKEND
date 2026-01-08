package com.restAPI.task_rest_api.Service;



import com.restAPI.task_rest_api.Model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookService {

    Book addBook(Book book);

    Optional<Book> getBookById(Long id);

    List<Book> getAllBooks();

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    // Separate methods for each functionality
    List<Book> getBooksByAuthor(String author);
    List<Book> getSortedBooks(String sortBy, String order);
    List<Book> getPaginatedBooks(int page, int size);
}


