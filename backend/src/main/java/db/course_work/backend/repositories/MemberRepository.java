package db.course_work.backend.repositories;

import db.course_work.backend.entities.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {}
