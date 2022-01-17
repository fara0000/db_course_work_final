package db.course_work.backend.repositories;

import db.course_work.backend.entities.Synagogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SynagogueRepository extends JpaRepository<Synagogue, Long> {
    @Query("select s from Member m inner join m.synagogue s where m.id = :memberId")
    Optional<Synagogue> findSynagogueByMemberId(@Param("memberId") Long memberId);
}
