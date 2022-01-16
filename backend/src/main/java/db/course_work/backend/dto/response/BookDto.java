package db.course_work.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@Builder
public class BookDto {
    private long id;
    private String name;
    private String description;
    @Nullable
    private Long borrowerId;
    private boolean available;
}
