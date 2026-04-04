package com.library.infrastructure.config;

import com.library.domain.port.api.BookServicePort;
import com.library.domain.port.spi.BookPersistencePort;
import com.library.domain.service.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ⚙️ SPRING CONFIGURATION - Wires everything together
 *
 * ┌─────────────────────────────────────────────────────────────┐
 * │  🎓 LEARNING POINT                                          │
 * ├─────────────────────────────────────────────────────────────┤
 * │  Remember: BookServiceImpl has NO Spring annotations!       │
 * │                                                             │
 * │  So how does Spring know about it?                          │
 * │  → THIS is where we create it as a Spring Bean!             │
 * │                                                             │
 * │  This keeps the domain pure while still using Spring DI.    │
 * └─────────────────────────────────────────────────────────────┘
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public BookServicePort bookServicePort(BookPersistencePort persistencePort) {
        // We create the domain service and inject the persistence adapter
        return new BookServiceImpl(persistencePort);
    }
}