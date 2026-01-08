package com.restAPI.task_rest_api.Service;

import com.restAPI.task_rest_api.Repository.BookRepository;
import org.springframework.stereotype.Service;

import com.restAPI.task_rest_api.Model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new IllegalArgumentException("Author is required");
        }
        return repository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }
    @Override
    public Book updateBook(Long id, Book book) {
        Book existingBook = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        return repository.update(id,existingBook);
    }
    @Override
    public void deleteBook(Long id){
        Book existingBook = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        repository.deleteById(id);

    }


}

