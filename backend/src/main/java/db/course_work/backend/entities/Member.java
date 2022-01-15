package db.course_work.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    @ManyToOne
    @JoinColumn(name = "synagogue_id")
    private Synagogue synagogue;
    @ManyToMany(mappedBy = "members")
    private List<Event> events;
}
