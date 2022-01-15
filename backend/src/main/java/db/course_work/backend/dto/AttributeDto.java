package db.course_work.backend.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AttributeDto {
    private String name;
    private String description;
}
