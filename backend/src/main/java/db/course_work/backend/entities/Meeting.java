package db.course_work.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Event event;
    @ManyToOne
    private Premise premise;
    @NotNull
    @Min(value = 1)
    private Integer maxVisitors;
    @Min(value = 0)
    private Float food;
}
