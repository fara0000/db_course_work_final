package db.course_work.backend.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "id")
public class Library extends Premise {
    @OneToOne
    @JoinColumn(name = "synagogue_id")
    @Setter(AccessLevel.NONE)
    private Synagogue housedInSynagogue;

    public void setHousedInSynagogue(Synagogue housedInSynagogue) {
        this.housedInSynagogue = housedInSynagogue;
        super.setSynagogue(housedInSynagogue);
    }
}
