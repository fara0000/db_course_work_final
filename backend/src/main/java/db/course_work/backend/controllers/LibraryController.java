package db.course_work.backend.controllers;

import db.course_work.backend.dto.BookDto;
import db.course_work.backend.dto.BookList;
import db.course_work.backend.entities.Book;
import db.course_work.backend.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class LibraryController {
    private final LibraryService libraryService;

    private BookDto convertBookToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .borrowerId(book.isAvailable() ? null : book.getBorrower().getId())
                .available(book.isAvailable())
                .build();
    }

    private BookList convertBooksToDto(List<Book> books) {
        List<BookDto> bookDtos = books.stream().map(this::convertBookToDto).collect(Collectors.toList());
        return new BookList(bookDtos);
    }

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("")
    @ResponseBody
    public BookList getAllBooks() {
        return convertBooksToDto(libraryService.getBooks(1));
    }

    @GetMapping(value = "", params = "available=true")
    @ResponseBody
    public BookList getAvailableBooks() {
        return convertBooksToDto(libraryService.getAvailableBooks(1));
    }

    @GetMapping(value = "/my")
    @ResponseBody
    public BookList getMemberBooks() {
        return convertBooksToDto(libraryService.getMemberBooks(1));
    }

    @PostMapping(value = "/{bookId}/borrow")
    @ResponseBody
    public BookDto borrowBook(@PathVariable("bookId") long bookId) {
        Optional<Book> bookOptional = libraryService.takeBook(1, bookId);
        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Available book with provided id not found");
        }
        return convertBookToDto(bookOptional.get());
    }
}
