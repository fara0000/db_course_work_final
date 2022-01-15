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

    public LibraryService(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    private Member getMember(long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) throw new UserNotFoundException();
        return memberOptional.get();
    }

    private long getLibraryId(Member member) {
        return member.getSynagogue().getLibrary().getId();
    }

    public List<Book> getBooks(long memberId) {
        Member member = getMember(memberId);
        return bookRepository.findByLibraryId(getLibraryId(member));
    }

    public List<Book> getAvailableBooks(long memberId) {
        Member member = getMember(memberId);
        return bookRepository.findByLibraryIdAndAvailableTrue(getLibraryId(member));
    }

    public List<Book> getMemberBooks(long memberId) {
        Member member = getMember(memberId);
        return bookRepository.findByLibraryIdAndBorrowerId(getLibraryId(member), memberId);
    }

    @Transactional
    public Optional<Book> takeBook(long memberId, long bookId) {
        Member member = getMember(memberId);
        Optional<Book> optionalBook = bookRepository.findByIdAndLibraryId(bookId, getLibraryId(member));
        if (optionalBook.isEmpty()) return Optional.empty();
        Book book = optionalBook.get();
        if (!book.isAvailable()) return Optional.empty();
        book.setBorrower(memberRepository.findById(memberId).orElseThrow(
                UserNotFoundException::new
        ));
        return Optional.of(book);
    }
}
