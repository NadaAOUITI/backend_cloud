package com.library.application.rest.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 📥 REQUEST DTO - Data coming IN from the client
 * 
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  DTOs (Data Transfer Objects) are different from Domain!    │
 * │                                                             │
 * │  Why separate DTOs?                                         │
 * │  • Security: Control what data is accepted                  │
 * │  • Validation: Add validation annotations                   │
 * │  • Decoupling: API can change without affecting domain      │
 * └─────────────────────────────────────────────────────────────┘
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author cannot exceed 255 characters")
    private String author;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @NotNull(message = "Publication date is required")
    @PastOrPresent(message = "Publication date cannot be in the future")
    private LocalDate publicationDate;

    @Positive(message = "Number of pages must be positive")
    private Integer numberOfPages;

    @NotBlank(message = "Genre is required")
    private String genre;

    private Boolean available;
}