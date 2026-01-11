package com.springSecurity.hands_on.service;

import com.springSecurity.hands_on.Repositories.BookRepository;
import com.springSecurity.hands_on.Repositories.UserRepository;
import com.springSecurity.hands_on.SecurityConstants;
import com.springSecurity.hands_on.dto.LoginRequestDTO;
import com.springSecurity.hands_on.dto.LoginResponseDTO;
import com.springSecurity.hands_on.dto.UserRequestDTO;
import com.springSecurity.hands_on.dto.UserResponseDTO;
import com.springSecurity.hands_on.entity.Book;
import com.springSecurity.hands_on.entity.User;
import com.springSecurity.hands_on.exceptions.InvalidCredentialsException;
import com.springSecurity.hands_on.exceptions.UnauthorizedException;
import com.springSecurity.hands_on.exceptions.UserNotFoundException;
import com.springSecurity.hands_on.security.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ExecutorService executorService;
    private static final Logger logger =
            LoggerFactory.getLogger(BookService.class);
    public BookService(BookRepository bookRepository, ExecutorService executorService) {
        this.bookRepository = bookRepository;
        this.executorService = executorService;
    }


    public Book addBook(Book book) {

        if (book.getTitle() == null || book.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().isBlank()) {
            throw new IllegalArgumentException("Author is required");
        }

        //Initial state
        book.setStatus("PROCESSING");

        // Save once
        Book savedBook = bookRepository.save(book);
        Long bookId = Long.valueOf(savedBook.getId());

        //Background task
        executorService.submit(() -> {
            try {
                logger.info("Background thread started for book {}", savedBook.getId());
                Thread.sleep(10000);

                // FIND inside background thread
                Book foundBook = bookRepository
                        .findById(bookId)
                        .orElseThrow();

                foundBook.setStatus("AVAILABLE");
                bookRepository.save(foundBook);
                logger.info("Background thread finished for book {}", foundBook.getId());

            } catch (Exception e) {
                bookRepository.findById(bookId).ifPresent(b -> {
                    b.setStatus("FAILED");
                    bookRepository.save(b);
                });
            }
        });

        // 4️⃣ Return immediately
        return savedBook;
    }

    public Map<String, Long> getBooksByStatus() {

        return bookRepository.findAll().stream().collect(Collectors.groupingBy(
                Book::getStatus,
                Collectors.counting()
        ));
    }




    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        return bookRepository.update(id,existingBook);
    }
    public void deleteBook(Long id){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.deleteById(id);

    }
}
