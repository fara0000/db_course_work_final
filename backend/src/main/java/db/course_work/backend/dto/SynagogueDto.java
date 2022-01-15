package db.course_work.backend.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import db.course_work.backend.entities.Library;
import db.course_work.backend.entities.Premise;
import db.course_work.backend.entities.Tradition;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class SynagogueDto {
    private long id;
    private String name;
    private Float size;
    private String architectureStyle;
    @JsonManagedReference
    private Tradition tradition;
    @JsonManagedReference
    private List<PremiseDto> premises;
    @JsonManagedReference
    private PremiseDto library;
}
