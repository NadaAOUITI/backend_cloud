package com.library.infrastructure.adapter.persistence.repository;

import com.library.infrastructure.adapter.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 💾 Spring Data JPA Repository
 *
 * Spring automatically implements these methods based on their names!
 */
@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<BookEntity> findByAuthorContainingIgnoreCase(String author);

    List<BookEntity> findByGenreIgnoreCase(String genre);

    List<BookEntity> findByAvailableTrue();
}