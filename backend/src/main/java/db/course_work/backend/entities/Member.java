package db.course_work.backend.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, max = 50)
    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @Size(min = 4, max = 50)
    @Column(name = "surname")
    private String surname;

    @Size(min = 2, max = 50)
    @Column(name = "login", nullable = false, unique = true)
    @NotEmpty
    private String login;

    @Size(min = 4)
    @Column(name = "password", nullable = false, unique = true)
    @NotEmpty
    private String password;

    @Column(name = "role", nullable = false)
    @NotEmpty
    private String role;

    @ManyToOne
    @JoinColumn(name = "synagogue")
    private Synagogue synagogue;
}
