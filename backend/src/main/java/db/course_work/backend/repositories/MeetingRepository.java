package db.course_work.backend.repositories;

import db.course_work.backend.entities.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    List<Meeting> findBySynagogueIdAndDateAfter(long synagogueId, OffsetDateTime dateTime);
    List<Meeting> findBySynagogueId(long synagogueId);
}
