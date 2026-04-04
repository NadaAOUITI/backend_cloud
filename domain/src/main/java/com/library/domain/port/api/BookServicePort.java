package com.library.domain.port.api;

import com.library.domain.model.Book;
import java.util.List;

/**
 * 🔌 API PORT - What our domain PROVIDES to the outside world
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  This is the "menu" of operations our domain offers.        │
 * │                                                             │
 * │  • Defined IN the domain                                    │
 * │  • IMPLEMENTED BY the domain (BookServiceImpl)              │
 * │  • CALLED BY external adapters (BookController)             │
 * │                                                             │
 * │      Controller ──────▶ BookServicePort ──▶ Implementation  │
 * │      (outside)          (interface)         (domain)        │
 * └─────────────────────────────────────────────────────────────┘
 */
public interface BookServicePort {

    // ══════════════ COMMANDS (modify data) ══════════════

    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);

    // ══════════════ QUERIES (read data) ══════════════

    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book getBookByIsbn(String isbn);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByGenre(String genre);
    List<Book> getAvailableBooks();
}