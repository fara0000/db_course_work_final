package db.course_work.backend.dto;

import db.course_work.backend.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookList {
    private List<BookDto> books;
}
