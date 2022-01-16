package db.course_work.backend.dto.response;

import db.course_work.backend.dto.response.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookList {
    private List<BookDto> books;
}
