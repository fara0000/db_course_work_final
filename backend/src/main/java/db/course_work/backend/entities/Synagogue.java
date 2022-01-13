package db.course_work.backend.entities;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.*;

@Data
@Entity
@Table(name = "synagogue")
public class Synagogue {
    @Id
    @Column(name = "synagogue_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 4, max = 30)
    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Size(min = 1)
    @Column(name = "size")
    @NotEmpty
    private Float size;

    @Size(min = 4, max = 50)
    @Column(name = "architecture_style")
    private String style;

    @ManyToOne
    @JoinColumn(name = "tradition")
    private Tradition tradition;
}
