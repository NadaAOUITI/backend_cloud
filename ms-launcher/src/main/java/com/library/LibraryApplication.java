package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 🚀 MAIN APPLICATION
 */
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        System.out.println("""
            
            ╔═══════════════════════════════════════════════════════════╗
            ║   📚 LIBRARY MANAGEMENT SYSTEM                            ║
            ║   Hexagonal Architecture with Spring Boot                 ║
            ╚═══════════════════════════════════════════════════════════╝
            """);
        
        SpringApplication.run(LibraryApplication.class, args);
        
        System.out.println("""
            
            ╔═══════════════════════════════════════════════════════════╗
            ║   ✅ Application started successfully!                    ║
            ║                                                           ║
            ║   📖 Swagger UI:  http://localhost:8080/swagger-ui.html   ║
            ║   ❤️  Health:     http://localhost:8080/health              ║
            ╚═══════════════════════════════════════════════════════════╝
            """);
    }
}