package db.course_work.backend.dto.response;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class EventDto {
    private long id;
    private String type;
    private String description;
    private OffsetDateTime date;
    private long memberCount;
}
