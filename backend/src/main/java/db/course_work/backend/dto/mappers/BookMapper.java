package db.course_work.backend.dto.mappers;

import db.course_work.backend.dto.response.BookDto;
import db.course_work.backend.dto.response.BookList;
import db.course_work.backend.entities.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public BookDto convertBookToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .borrowerId(book.isAvailable() ? null : book.getBorrower().getId())
                .available(book.isAvailable())
                .build();
    }

    public BookList convertBooksToDto(List<Book> books) {
        List<BookDto> bookDtos = books.stream().map(this::convertBookToDto).collect(Collectors.toList());
        return new BookList(bookDtos);
    }
}
