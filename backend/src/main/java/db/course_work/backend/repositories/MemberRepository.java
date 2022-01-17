package db.course_work.backend.repositories;

import db.course_work.backend.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Set<Member> findMembersByIdIn(List<Long> ids);

    Set<Member> findMembersBySynagogueId(Long synagogueId);

    Member findMemberByLogin(String login);
}
