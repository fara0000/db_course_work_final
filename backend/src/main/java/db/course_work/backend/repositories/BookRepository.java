package db.course_work.backend.repositories;

import db.course_work.backend.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByLibrary_SynagogueId(@Param("synagogue_id") Long id);
    List<Book> findByLibrary_SynagogueIdAndAvailableTrue(@Param("synagogue_id") Long id);
    List<Book> findByLibraryIdAndAvailableTrue(@Param("library_id") Long id);
    List<Book> findByLibraryIdAndBorrowerId(@Param("library_id") Long id, @Param("borrower_id") Long borrowerId);
    Optional<Book> findByIdAndLibraryId(long id, @Param("library_id") Long libraryId);
}
