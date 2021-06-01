package wsb.demo.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import wsb.demo.validators.UniqueUsername;
import wsb.demo.validators.ValidPasswords;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ValidPasswords
@UniqueUsername
public class Person {


    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Size(min =5, max=100)
    @Column(nullable = false, unique = true)
    String username;


    @Column(nullable = false)
    String password;

    @Transient
    String repeatedPassword;

    @NotEmpty
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enable = true;

    @Email
    @NotEmpty
    @Column(nullable = false)
    String mail;

    @NotEmpty
    @Size(min =5, max=10)
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
    public Boolean canBeEditedBy(String username) {
        return this.username.equals(username);
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
