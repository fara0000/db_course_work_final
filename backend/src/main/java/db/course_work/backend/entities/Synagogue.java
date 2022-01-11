package db.course_work.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
public class Synagogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 4, max = 50)
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    @NotNull
    @Min(value = 1)
    @Max(value = 100000)
    private Float size;
    @ManyToOne
    private Tradition tradition;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "synagogue")
    private List<Premise> premises;
}
