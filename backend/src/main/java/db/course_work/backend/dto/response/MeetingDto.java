package db.course_work.backend.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class MeetingDto extends EventDto {
    private PremiseDto premise;
    private Integer maxVisitors;
    private Float food;

    @Builder(builderMethodName = "meetingBuilder")
    public MeetingDto(long id, String type, String description, OffsetDateTime date, long memberCount, PremiseDto premise, Integer maxVisitors, Float food) {
        super(id, type, description, date, memberCount);
        this.premise = premise;
        this.maxVisitors = maxVisitors;
        this.food = food;
    }
}
