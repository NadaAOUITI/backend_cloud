package com.library.domain.port.spi;

import com.library.domain.model.Book;
import java.util.List;
import java.util.Optional;

/**
 * 🔌 SPI PORT - What our domain NEEDS from the outside world
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  SPI = Service Provider Interface                           │
 * │                                                             │
 * │  This is a "contract" that external systems must fulfill.   │
 * │                                                             │
 * │  • Defined IN the domain                                    │
 * │  • IMPLEMENTED BY infrastructure (BookPersistenceAdapter)   │
 * │  • CALLED BY domain services (BookServiceImpl)              │
 * │                                                             │
 * │      Domain ──────▶ BookPersistencePort ──▶ Database Adapter│
 * │      (inside)       (interface)             (outside)       │
 * └─────────────────────────────────────────────────────────────┘
 */
public interface BookPersistencePort {

    // ══════════════ WRITE OPERATIONS ══════════════

    Book save(Book book);
    Book update(Book book);
    void deleteById(Long id);

    // ══════════════ READ OPERATIONS ══════════════

    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByAvailableTrue();

    // ══════════════ EXISTENCE CHECKS ══════════════

    boolean existsById(Long id);
    boolean existsByIsbn(String isbn);
}