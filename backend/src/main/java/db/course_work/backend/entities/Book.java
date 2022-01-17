package db.course_work.backend.entities;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(max = 50)
    @NotEmpty
    private String name;
    @NotNull
    @Size(max = 250)
    @NotEmpty
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;
    @Setter(value= AccessLevel.NONE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private Member borrower;
    @Setter(value= AccessLevel.NONE)
    @Formula("borrower_id IS NULL")
    private boolean available;


    public void setBorrower(Member borrower) {
        this.borrower = borrower;
        this.available = borrower == null;
    }
}
