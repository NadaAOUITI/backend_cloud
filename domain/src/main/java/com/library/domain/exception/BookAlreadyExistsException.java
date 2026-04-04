package com.library.domain.exception;

/**
 * 🚫 Thrown when trying to create a book with existing ISBN
 */
public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("A book with ISBN '" + isbn + "' already exists");
    }
}