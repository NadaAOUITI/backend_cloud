package com.library.infrastructure.adapter.persistence.mapper;

import com.library.domain.model.Book;
import com.library.infrastructure.adapter.persistence.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * 🔄 MAPPER - Converts between Domain Book and JPA BookEntity
 *
 * MapStruct generates the implementation automatically at compile time!
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookEntityMapper {

    // Domain → Entity (for saving to database)
    BookEntity toEntity(Book book);

    // Entity → Domain (for reading from database)
    Book toDomain(BookEntity entity);

    // List conversion
    List<Book> toDomainList(List<BookEntity> entities);
}