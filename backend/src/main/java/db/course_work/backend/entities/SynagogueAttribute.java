package db.course_work.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class SynagogueAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Attribute attribute;
    @ManyToOne
    @JoinColumn(name = "premise_id")
    private Premise premise;
    @NotNull
    @Size(max = 250)
    @NotEmpty
    private String description;
}
