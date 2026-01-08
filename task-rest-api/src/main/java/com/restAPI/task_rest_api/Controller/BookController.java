package com.restAPI.task_rest_api.Controller;

import com.restAPI.task_rest_api.Service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restAPI.task_rest_api.Model.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = service.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

        Book updatedBook = service.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id)
    {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


}
