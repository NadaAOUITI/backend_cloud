package com.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 📚 DOMAIN ENTITY: Book
 *
 * This is a PURE domain object - no framework annotations!
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  Notice: NO @Entity, NO @Table, NO @Column                  │
 * │  This class knows NOTHING about databases!                  │
 * │  It only contains business logic and data.                  │
 * └─────────────────────────────────────────────────────────────┘
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String description;
    private LocalDate publicationDate;
    private Integer numberOfPages;
    private String genre;
    private Boolean available;

    // ══════════════════════════════════════════════════════════════
    // 🎯 BUSINESS LOGIC METHODS
    // These contain our business rules!
    // ══════════════════════════════════════════════════════════════

    /**
     * Business Rule: Can this book be borrowed?
     */
    public boolean canBeBorrowed() {
        return Boolean.TRUE.equals(available);
    }

    /**
     * Business Rule: Mark book as borrowed
     */
    public void markAsBorrowed() {
        if (!canBeBorrowed()) {
            throw new IllegalStateException(
                    "Cannot borrow book '" + title + "': it is not available"
            );
        }
        this.available = false;
    }

    /**
     * Business Rule: Mark book as returned
     */
    public void markAsReturned() {
        this.available = true;
    }

    /**
     * Business Rule: Is this a long book? (more than 500 pages)
     */
    public boolean isLongBook() {
        return numberOfPages != null && numberOfPages > 500;
    }

    /**
     * Business Rule: Is this a classic? (published more than 25 years ago)
     */
    public boolean isClassic() {
        if (publicationDate == null) return false;
        return publicationDate.isBefore(LocalDate.now().minusYears(25));
    }
}