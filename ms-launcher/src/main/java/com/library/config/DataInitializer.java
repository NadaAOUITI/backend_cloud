package com.library.config;

import com.library.domain.model.Book;
import com.library.domain.port.api.BookServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 📦 DATA INITIALIZER - Loads sample books on startup (local H2 only).
 */
@Component
@Profile("local")
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BookServicePort bookService;

    @Override
    public void run(String... args) {
        System.out.println("\n📦 Loading sample books...\n");

        // Book 1
        bookService.addBook(Book.builder()
                .isbn("978-0-13-468599-1")
                .title("Clean Architecture")
                .author("Robert C. Martin")
                .description("A guide to software structure and design")
                .publicationDate(LocalDate.of(2017, 9, 10))
                .numberOfPages(432)
                .genre("Software Engineering")
                .available(true)
                .build());

        // Book 2
        bookService.addBook(Book.builder()
                .isbn("978-0-13-235088-4")
                .title("Clean Code")
                .author("Robert C. Martin")
                .description("A handbook of agile software craftsmanship")
                .publicationDate(LocalDate.of(2008, 8, 1))
                .numberOfPages(464)
                .genre("Software Engineering")
                .available(true)
                .build());

        // Book 3
        bookService.addBook(Book.builder()
                .isbn("978-0-201-63361-0")
                .title("Design Patterns")
                .author("Gang of Four")
                .description("Elements of reusable object-oriented software")
                .publicationDate(LocalDate.of(1994, 10, 31))
                .numberOfPages(395)
                .genre("Software Engineering")
                .available(true)
                .build());

        // Book 4
        bookService.addBook(Book.builder()
                .isbn("978-0-32-112521-7")
                .title("Domain-Driven Design")
                .author("Eric Evans")
                .description("Tackling complexity in the heart of software")
                .publicationDate(LocalDate.of(2003, 8, 30))
                .numberOfPages(560)
                .genre("Software Engineering")
                .available(false)
                .build());

        // Book 5
        bookService.addBook(Book.builder()
                .isbn("978-0-59-651774-8")
                .title("JavaScript: The Good Parts")
                .author("Douglas Crockford")
                .description("Unearthing the excellence in JavaScript")
                .publicationDate(LocalDate.of(2008, 5, 1))
                .numberOfPages(176)
                .genre("Programming")
                .available(true)
                .build());

        // Book 6
        bookService.addBook(Book.builder()
                .isbn("978-0-13-110362-7")
                .title("The C Programming Language")
                .author("Brian Kernighan, Dennis Ritchie")
                .description("The classic C programming book")
                .publicationDate(LocalDate.of(1988, 4, 1))
                .numberOfPages(272)
                .genre("Programming")
                .available(false)
                .build());

        System.out.println("✅ Loaded " + bookService.getAllBooks().size() + " books!\n");
    }
}