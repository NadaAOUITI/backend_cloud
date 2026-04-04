package com.library.infrastructure.adapter.persistence;

import com.library.domain.model.Book;
import com.library.domain.port.spi.BookPersistencePort;
import com.library.infrastructure.adapter.persistence.entity.BookEntity;
import com.library.infrastructure.adapter.persistence.mapper.BookEntityMapper;
import com.library.infrastructure.adapter.persistence.repository.BookJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 🔌 PERSISTENCE ADAPTER - Implements the SPI Port
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  This adapter:                                              │
 * │  1. IMPLEMENTS BookPersistencePort (defined in domain)      │
 * │  2. USES Spring Data JPA internally                         │
 * │  3. CONVERTS between domain Book and JPA BookEntity         │
 * │                                                             │
 * │  The domain service knows NOTHING about:                    │
 * │  • JPA or Hibernate                                         │
 * │  • H2 or PostgreSQL                                         │
 * │  • BookEntity class                                         │
 * │                                                             │
 * │  This is the power of Hexagonal Architecture!               │
 * └─────────────────────────────────────────────────────────────┘
 */
@Component
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookPersistencePort {

    private final BookJpaRepository repository;
    private final BookEntityMapper mapper;

    // ══════════════ WRITE OPERATIONS ══════════════

    @Override
    public Book save(Book book) {
        BookEntity entity = mapper.toEntity(book);
        BookEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Book update(Book book) {
        BookEntity entity = mapper.toEntity(book);
        BookEntity updated = repository.save(entity);
        return mapper.toDomain(updated);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // ══════════════ READ OPERATIONS ══════════════

    @Override
    public List<Book> findAll() {
        return mapper.toDomainList(repository.findAll());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return repository.findByIsbn(isbn).map(mapper::toDomain);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return mapper.toDomainList(repository.findByAuthorContainingIgnoreCase(author));
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return mapper.toDomainList(repository.findByGenreIgnoreCase(genre));
    }

    @Override
    public List<Book> findByAvailableTrue() {
        return mapper.toDomainList(repository.findByAvailableTrue());
    }

    // ══════════════ EXISTENCE CHECKS ══════════════

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }
}