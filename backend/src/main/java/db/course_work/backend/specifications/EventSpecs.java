package db.course_work.backend.specifications;

import db.course_work.backend.entities.Event;
import db.course_work.backend.entities.Member;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.SetJoin;
import java.time.OffsetDateTime;

public class EventSpecs {
    public static <T extends Event> Specification<T> dateAfter(OffsetDateTime offsetDateTime) {
        return (event, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(event.get("date"), offsetDateTime);
    }

    public static <T extends Event> Specification<T> hasMemberWithId(long id) {
        return (event, criteriaQuery, criteriaBuilder) -> {
            SetJoin<T, Member> members = event.joinSet("members");
            return criteriaBuilder.equal(members.get("id"), id);
        };
    }

    public static <T extends Event> Specification<T> hasSynagogue(long synagogueId) {
        return (event, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(event.get("synagogue").get("id"), synagogueId);
    }
}

