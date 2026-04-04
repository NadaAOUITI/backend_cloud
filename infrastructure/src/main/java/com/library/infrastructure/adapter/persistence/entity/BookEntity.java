package com.library.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 💾 JPA ENTITY - Database representation of Book
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  This is DIFFERENT from the domain Book class!              │
 * │                                                             │
 * │  Domain Book    → Business logic, no database knowledge     │
 * │  BookEntity     → Database mapping, no business logic       │
 * │                                                             │
 * │  We convert between them using a Mapper.                    │
 * └─────────────────────────────────────────────────────────────┘
 */
@Entity
@Table(name = "books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 2000)
    private String description;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    private String genre;

    @Column(nullable = false)
    private Boolean available = true;
}