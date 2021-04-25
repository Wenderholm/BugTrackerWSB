package wsb.demo.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enable = true;

    @Column(nullable = false)
    String mail;

    @Column(nullable = false)
    String phone;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_authorities",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Authority> authorities = new HashSet<>();

    public Person(String username, String password, String name,Boolean enable, String mail, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.enable = enable;
        this.mail = mail;
        this.phone = phone;
    }

    public Person(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
