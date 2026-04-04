package com.library.domain.service;

import com.library.domain.exception.BookAlreadyExistsException;
import com.library.domain.exception.BookNotFoundException;
import com.library.domain.model.Book;
import com.library.domain.port.api.BookServicePort;
import com.library.domain.port.spi.BookPersistencePort;

import java.util.List;

/**
 * 🎯 DOMAIN SERVICE - Contains all business logic
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  NOTICE: There are NO Spring annotations here!              │
 * │                                                             │
 * │  ❌ No @Service                                              │
 * │  ❌ No @Autowired                                            │
 * │  ❌ No @Transactional                                        │
 * │                                                             │
 * │  This is PURE Java! Spring configuration happens in the     │
 * │  infrastructure module (BeanConfiguration.java)             │
 * └─────────────────────────────────────────────────────────────┘
 */
public class BookServiceImpl implements BookServicePort {

    private final BookPersistencePort persistencePort;

    // Constructor injection - no @Autowired needed!
    public BookServiceImpl(BookPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    // ══════════════════════════════════════════════════════════════
    // COMMAND OPERATIONS
    // ══════════════════════════════════════════════════════════════

    @Override
    public Book addBook(Book book) {
        // 📋 Business Rule: ISBN must be unique
        if (persistencePort.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }

        // 📋 Business Rule: New books are available by default
        if (book.getAvailable() == null) {
            book.setAvailable(true);
        }

        return persistencePort.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        // First, check if book exists
        Book existingBook = getBookById(id);

        // 📋 Business Rule: Cannot change ISBN to one that already exists
        boolean isbnChanged = !existingBook.getIsbn().equals(book.getIsbn());
        if (isbnChanged && persistencePort.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistsException(book.getIsbn());
        }

        // Update all fields
        existingBook.setIsbn(book.getIsbn());
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setDescription(book.getDescription());
        existingBook.setPublicationDate(book.getPublicationDate());
        existingBook.setNumberOfPages(book.getNumberOfPages());
        existingBook.setGenre(book.getGenre());
        existingBook.setAvailable(book.getAvailable());

        return persistencePort.update(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        // 📋 Business Rule: Cannot delete non-existent book
        if (!persistencePort.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        persistencePort.deleteById(id);
    }

    // ══════════════════════════════════════════════════════════════
    // QUERY OPERATIONS
    // ══════════════════════════════════════════════════════════════

    @Override
    public List<Book> getAllBooks() {
        return persistencePort.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return persistencePort.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return persistencePort.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return persistencePort.findByAuthor(author);
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return persistencePort.findByGenre(genre);
    }

    @Override
    public List<Book> getAvailableBooks() {
        return persistencePort.findByAvailableTrue();
    }
}