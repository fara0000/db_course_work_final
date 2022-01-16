package db.course_work.backend.repositories;

import db.course_work.backend.entities.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Set<Member> findMembersByIdIn(List<Long> ids);
    Set<Member> findMembersBySynagogueId(Long synagogueId);
}
