package db.course_work.backend.repositories;

import db.course_work.backend.entities.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
    List<Member> findMembersByIdIn(List<Long> ids);
    List<Member> findMembersBySynagogueId(Long synagogueId);
}
