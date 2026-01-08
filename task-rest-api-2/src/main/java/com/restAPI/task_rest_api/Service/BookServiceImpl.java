package com.restAPI.task_rest_api.Service;

import com.restAPI.task_rest_api.Repository.BookRepository;
import org.springframework.stereotype.Service;
//import org.springframework.data.domain.Sort;

import com.restAPI.task_rest_api.Model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
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
        return repository.update(id, existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        repository.deleteById(id);
    }

    // 🔹 Filtering
    @Override
    public List<Book> getBooksByAuthor(String author) {
        return repository.findAll().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    // 🔹 Sorting
    @Override
    public List<Book> getSortedBooks(String sortBy, String order) {
        List<Book> books = repository.findAll();

        // Only allow "title" as a valid sortBy
        if (!"title".equalsIgnoreCase(sortBy)) {
            throw new IllegalArgumentException("Sorting is only supported by title");
        }

        // Comparator for title
        Comparator<Book> comparator = Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);

        // Reverse if descending
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        books.sort(comparator);
        return books;
    }


    // 🔹 Pagination
    @Override
    public List<Book> getPaginatedBooks(int page, int size) {
        List<Book> books = repository.findAll();

        int total = books.size();
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, total);

        if (fromIndex > total) return new ArrayList<>();

        return books.subList(fromIndex, toIndex);
    }
}



