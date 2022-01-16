package db.course_work.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import db.course_work.backend.entities.Event;
import db.course_work.backend.entities.Synagogue;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Builder
public class MemberDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String name;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String surname;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String role;
    @JsonBackReference
    private Synagogue synagogue;
    @JsonBackReference
    private Set<Event> events;
}
