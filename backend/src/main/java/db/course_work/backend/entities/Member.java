package db.course_work.backend.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "member")
public class Member implements UserDetails {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 50)
    @Column(name = "name", nullable = false)
    @NotEmpty
    private String name;

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
    @NotNull
    @JoinColumn(name = "synagogue")
    private Synagogue synagogue;

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
