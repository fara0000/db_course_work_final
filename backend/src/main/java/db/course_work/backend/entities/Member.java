package db.course_work.backend.entities;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 2, max = 50)
    @NotNull
    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

    @Column(name = "surname")
    private String surname;

    @Size(min = 2, max = 50)
    @NotNull
    @Column(name = "login", nullable = false, unique = true)
    @NotEmpty
    private String login;

    @Size(min = 4)
    @NotNull
    @Column(name = "password", nullable = false, unique = true)
    @NotEmpty
    private String password;

    @Column(name = "role", nullable = false)
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String role;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "synagogue_id")
    private Synagogue synagogue;

    @ManyToMany(mappedBy = "members")
    private Set<Event> events;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("Member"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
