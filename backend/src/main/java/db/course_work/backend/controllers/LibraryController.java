package db.course_work.backend.controllers;

import db.course_work.backend.dto.mappers.BookMapper;
import db.course_work.backend.dto.response.BookDto;
import db.course_work.backend.dto.response.BookList;
import db.course_work.backend.entities.Book;
import db.course_work.backend.entities.Member;
import db.course_work.backend.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
    public BookList getAllBooks(@AuthenticationPrincipal Member user) {
        return bookMapper.convertBooksToDto(libraryService.getBooksInSynagogue(user.getSynagogue().getId()));
    }

    @GetMapping(value = "", params = "available=true")
    @ResponseBody
    public BookList getAvailableBooks(@AuthenticationPrincipal Member user) {
        return bookMapper.convertBooksToDto(libraryService.getAvailableBooksInSynagogue(user.getSynagogue().getId()));
    }

    @GetMapping(value = "/my")
    @ResponseBody
    public BookList getMemberBooks(@AuthenticationPrincipal Member user) {
        return bookMapper.convertBooksToDto(libraryService.getMemberBooks(user.getId()));
    }

    @PostMapping(value = "/{bookId}/borrow")
    @ResponseBody
    public BookDto borrowBook(@PathVariable("bookId") long bookId, @AuthenticationPrincipal Member user) {
        Optional<Book> bookOptional = libraryService.takeBook(user.getId(), bookId);
        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Available book with provided id not found");
        }
        return bookMapper.convertBookToDto(bookOptional.get());
    }

    @PostMapping(value = "/{bookId}/return")
    @ResponseBody
    public String returnBook(@PathVariable("bookId") long bookId, @AuthenticationPrincipal Member user) {
        boolean isReturned = libraryService.returnBook(user.getId(), bookId);
        if (!isReturned) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member's book not found");
        return "Book successfully returned";
    }
}
