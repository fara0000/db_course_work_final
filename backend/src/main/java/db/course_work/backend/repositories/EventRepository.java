package db.course_work.backend.repositories;

import db.course_work.backend.entities.Event;
import org.springframework.data.repository.CrudRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findBySynagogueIdAndDateAfter(long synagogueId, OffsetDateTime dateTime);
    List<Event> findBySynagogueId(long synagogueId);
}
