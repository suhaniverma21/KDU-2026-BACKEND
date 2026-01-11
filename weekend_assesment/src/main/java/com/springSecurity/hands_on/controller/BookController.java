package com.springSecurity.hands_on.controller;

import com.springSecurity.hands_on.entity.Book;
import com.springSecurity.hands_on.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;
    }

    //getting all books stored in memory repository
    @GetMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN','MEMBER')")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/analytics/audit")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public Map<String, Long> getBooks() {

        return service.getBooksByStatus();
    }
    @Operation(summary = "Starts the async book processing")
    @PostMapping
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        //book.setStatus("PROCESSING");
        Book savedBook = service.addBook(book);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedBook);
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
