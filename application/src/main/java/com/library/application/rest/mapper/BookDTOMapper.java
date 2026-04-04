package com.library.application.rest.mapper;

import com.library.application.rest.dto.BookRequestDTO;
import com.library.application.rest.dto.BookResponseDTO;
import com.library.domain.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * 🔄 DTO MAPPER - Converts between DTOs and Domain objects
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookDTOMapper {

    // Request DTO → Domain (ignore ID, it will be generated)
    @Mapping(target = "id", ignore = true)
    Book toDomain(BookRequestDTO dto);

    // Domain → Response DTO (include computed fields)
    @Mapping(target = "isClassic", expression = "java(book.isClassic())")
    @Mapping(target = "isLongBook", expression = "java(book.isLongBook())")
    BookResponseDTO toResponse(Book book);

    // List conversion
    List<BookResponseDTO> toResponseList(List<Book> books);
}