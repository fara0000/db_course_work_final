package db.course_work.backend.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class MeetingRequest extends EventRequest {
    @NotNull
    private Long premiseId;
    @NotNull
    @Min(value = 1)
    private Integer maxVisitors;
    @Min(value = 0)
    private Float food;
}
