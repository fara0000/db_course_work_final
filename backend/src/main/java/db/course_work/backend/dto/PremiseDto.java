package db.course_work.backend.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import db.course_work.backend.entities.Synagogue;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class PremiseDto {
    private String name;
    @JsonBackReference
    private Synagogue synagogue;
    @JsonManagedReference
    private List<AttributeDto> attributes;
}
