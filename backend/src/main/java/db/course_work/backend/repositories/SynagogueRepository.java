package db.course_work.backend.repositories;

import db.course_work.backend.entities.Synagogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SynagogueRepository extends JpaRepository<Synagogue, Integer> {
}
