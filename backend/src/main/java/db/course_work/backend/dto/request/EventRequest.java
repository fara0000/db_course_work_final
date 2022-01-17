package db.course_work.backend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class EventRequest {
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String type;
    @NotNull
    @Size(max = 250)
    @NotEmpty
    private String description;
    @NotNull
    private OffsetDateTime date;
    @NotNull
    private List<Long> memberIds;
}
