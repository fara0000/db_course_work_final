package db.course_work.backend.entities;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tradition")
public class Tradition {
    @Id
    @Column(name = "tradition_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 50)
    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Size(max = 250)
    @Column(name = "description")
    private String description;
}
