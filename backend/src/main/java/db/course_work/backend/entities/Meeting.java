package db.course_work.backend.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Meeting extends Event {
    @ManyToOne
    private Premise premise;
    @NotNull
    @Min(value = 1)
    private Integer maxVisitors;
    @Min(value = 0)
    private Float food;

    public Meeting(String type, String description, OffsetDateTime dateTime, Synagogue synagogue, Set<Member> members, Premise premise, Integer maxVisitors, Float food) {
        super(type, description, dateTime, synagogue, members);
        this.premise = premise;
        this.maxVisitors = maxVisitors;
        this.food = food;
    }

    public boolean addMember(Member member) {
        if (getMembers().size() == maxVisitors || !getMembers().add(member)) return false;
        member.getEvents().add(this);
        return true;
    }
}
