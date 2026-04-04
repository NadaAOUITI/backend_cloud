package com.library.application.rest.controller;

import com.library.application.rest.dto.BookRequestDTO;
import com.library.application.rest.dto.BookResponseDTO;
import com.library.application.rest.mapper.BookDTOMapper;
import com.library.domain.model.Book;
import com.library.domain.port.api.BookServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 🌐 REST CONTROLLER - Driving Adapter
 * 
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  This is a DRIVING ADAPTER (left side of hexagon).          │
 * │                                                             │
 * │  It:                                                        │
 * │  1. Receives HTTP requests                                  │
 * │  2. Validates input (using @Valid)                          │
 * │  3. Converts DTOs to domain objects                         │
 * │  4. Calls the domain service (via API Port)                 │
 * │  5. Converts results back to DTOs                           │
 * │  6. Returns HTTP responses                                  │
 * │                                                             │
 * │  The controller knows NOTHING about databases!              │
 * └─────────────────────────────────────────────────────────────┘
 */
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Tag(name = "📚 Books", description = "Book management API")
public class BookController {

    private final BookServicePort bookService;
    private final BookDTOMapper mapper;

    // ══════════════════════════════════════════════════════════════
    // CREATE
    // ══════════════════════════════════════════════════════════════

    @PostMapping
    @Operation(summary = "Add a new book")
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestBody BookRequestDTO request) {
        Book book = mapper.toDomain(request);
        Book created = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    // ══════════════════════════════════════════════════════════════
    // READ
    // ══════════════════════════════════════════════════════════════

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(mapper.toResponseList(books));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(mapper.toResponse(book));
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Get book by ISBN")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(mapper.toResponse(book));
    }

    @GetMapping("/author/{author}")
    @Operation(summary = "Get books by author")
    public ResponseEntity<List<BookResponseDTO>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(mapper.toResponseList(books));
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get books by genre")
    public ResponseEntity<List<BookResponseDTO>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(mapper.toResponseList(books));
    }

    @GetMapping("/available")
    @Operation(summary = "Get available books")
    public ResponseEntity<List<BookResponseDTO>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(mapper.toResponseList(books));
    }

    // ══════════════════════════════════════════════════════════════
    // UPDATE
    // ══════════════════════════════════════════════════════════════

    @PutMapping("/{id}")
    @Operation(summary = "Update a book")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDTO request) {
        Book book = mapper.toDomain(request);
        Book updated = bookService.updateBook(id, book);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    // ══════════════════════════════════════════════════════════════
    // DELETE
    // ══════════════════════════════════════════════════════════════

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}