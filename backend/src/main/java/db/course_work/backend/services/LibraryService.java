package db.course_work.backend.services;

import db.course_work.backend.entities.Book;
import db.course_work.backend.entities.Member;
import db.course_work.backend.exceptions.UserNotFoundException;
import db.course_work.backend.repositories.BookRepository;
import db.course_work.backend.repositories.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final SynagogueService synagogueService;

    public LibraryService(BookRepository bookRepository, MemberRepository memberRepository, SynagogueService synagogueService) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.synagogueService = synagogueService;
    }

    private long getLibraryId(Member member) {
        return member.getSynagogue().getLibrary().getId();
    }

    @Transactional
    public List<Book> getBooks(long memberId) {
        Member member = synagogueService.getMember(memberId);
        return bookRepository.findByLibraryId(getLibraryId(member));
    }

    @Transactional
    public List<Book> getAvailableBooks(long memberId) {
        Member member = synagogueService.getMember(memberId);
        return bookRepository.findByLibraryIdAndAvailableTrue(getLibraryId(member));
    }

    @Transactional
    public List<Book> getMemberBooks(long memberId) {
        Member member = synagogueService.getMember(memberId);
        return bookRepository.findByLibraryIdAndBorrowerId(getLibraryId(member), memberId);
    }

    @Transactional
    public Optional<Book> takeBook(long memberId, long bookId) {
        Member member = synagogueService.getMember(memberId);
        Optional<Book> optionalBook = bookRepository.findByIdAndLibraryId(bookId, getLibraryId(member));
        if (optionalBook.isEmpty()) return Optional.empty();
        Book book = optionalBook.get();
        if (!book.isAvailable()) return Optional.empty();
        book.setBorrower(memberRepository.findById(memberId).orElseThrow(
                UserNotFoundException::new
        ));
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Transactional
    public boolean returnBook(long memberId, long bookId) {
        Member member = synagogueService.getMember(memberId);
        Optional<Book> optionalBook = bookRepository.findByIdAndLibraryId(bookId, getLibraryId(member));
        if (optionalBook.isEmpty()) return false;
        Book book = optionalBook.get();
        if (book.isAvailable() || book.getBorrower().getId() != member.getId()) return false;
        book.setBorrower(null);
        bookRepository.save(book);
        return true;
    }
}
