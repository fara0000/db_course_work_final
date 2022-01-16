package db.course_work.backend.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tradition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;
    @Size(max = 250)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "tradition_attribute",
            joinColumns = @JoinColumn(name = "tradition_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id")
    )
    private List<Attribute> attributes;
}
