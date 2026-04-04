package com.library.domain.exception;

/**
 * 🚫 Thrown when a book is not found
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Book with ID " + id + " was not found");
    }

    public BookNotFoundException(String isbn) {
        super("Book with ISBN '" + isbn + "' was not found");
    }
}