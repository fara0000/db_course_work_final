package db.course_work.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Synagogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 4, max = 50)
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    @NotNull
    @Min(value = 1)
    @Max(value = 100000)
    private Float size;
    @Size(min = 4, max = 50)
    private String architectureStyle;
    @ManyToOne
    private Tradition tradition;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "synagogue", orphanRemoval = true)
    private List<Premise> premises;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "housedInSynagogue")
    private Library library;
}
