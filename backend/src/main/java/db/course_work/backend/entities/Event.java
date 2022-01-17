package db.course_work.backend.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String type;
    @NotNull
    @Size(max = 250)
    @NotEmpty
    private String description;
    @NotNull
    @Column(name = "date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime date;
    @ManyToOne
    private Synagogue synagogue;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(
            name = "event_member",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> members;

    public Event(String type, String description, OffsetDateTime dateTime, Synagogue synagogue, Set<Member> members) {
        this.type = type;
        this.description = description;
        this.date = dateTime;
        this.synagogue = synagogue;
        this.members = members;
    }
}
