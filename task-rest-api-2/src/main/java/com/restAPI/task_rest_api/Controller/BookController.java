package com.restAPI.task_rest_api.Controller;

import com.restAPI.task_rest_api.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//import org.springframework.data.domain.Sort;
import com.restAPI.task_rest_api.Model.Book;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;
    }
    @GetMapping("/api/v1")
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    //SIMILARLY YOU CAN ADD @GetMapping("/api/v2") if there are any changes
    @GetMapping("/api/filter")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return service.getBooksByAuthor(author);
    }

    @GetMapping("/{id}")
    public EntityModel<Book> getBookById(@PathVariable Long id) {
        Book book = service.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Wrap book in EntityModel
        EntityModel<Book> resource = EntityModel.of(book);

        // Self link: GET /books/{id}
        resource.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());

        // Link to all books: GET /books/api/v1
        resource.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("all-books"));

        return resource;
    }

    // 🔹 Sorting
    @GetMapping("/api/sort")
    public List<Book> getSortedBooks(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return service.getSortedBooks(sortBy, order);
    }

    // 🔹 Pagination
    @GetMapping("/api/page")
    public List<Book> getPaginatedBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getPaginatedBooks(page, size);
    }



    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book book) {
        Book savedBook = service.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {

        Book updatedBook = service.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id)
    {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


}
