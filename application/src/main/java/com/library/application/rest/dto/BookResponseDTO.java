package com.library.application.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 📤 RESPONSE DTO - Data going OUT to the client
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String description;
    private LocalDate publicationDate;
    private Integer numberOfPages;
    private String genre;
    private Boolean available;
    
    // Extra computed fields from domain logic
    private Boolean isClassic;
    private Boolean isLongBook;
}