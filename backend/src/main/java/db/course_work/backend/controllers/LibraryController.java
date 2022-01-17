package db.course_work.backend.controllers;

import db.course_work.backend.dto.mappers.BookMapper;
import db.course_work.backend.dto.response.BookDto;
import db.course_work.backend.dto.response.BookList;
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
    private final BookMapper bookMapper;

    public LibraryController(LibraryService libraryService, BookMapper bookMapper) {
        this.libraryService = libraryService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("")
    @ResponseBody
    public BookList getAllBooks() {
        return bookMapper.convertBooksToDto(libraryService.getBooks(1));
    }

    @GetMapping(value = "", params = "available=true")
    @ResponseBody
    public BookList getAvailableBooks() {
        return bookMapper.convertBooksToDto(libraryService.getAvailableBooks(1));
    }

    @GetMapping(value = "/my")
    @ResponseBody
    public BookList getMemberBooks() {
        return bookMapper.convertBooksToDto(libraryService.getMemberBooks(1));
    }

    @PostMapping(value = "/{bookId}/borrow")
    @ResponseBody
    public BookDto borrowBook(@PathVariable("bookId") long bookId) {
        Optional<Book> bookOptional = libraryService.takeBook(1, bookId);
        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Available book with provided id not found");
        }
        return bookMapper.convertBookToDto(bookOptional.get());
    }

    @PostMapping(value = "/{bookId}/return")
    @ResponseBody
    public String returnBook(@PathVariable("bookId") long bookId) {
        boolean isReturned = libraryService.returnBook(1, bookId);
        if (!isReturned) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member's book not found");
        return "Book successfully returned";
    }
}
