package wsb.demo.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wsb.demo.validators.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@UniqueUsername
public class PersonForm {


    Long id;

    @NotEmpty
    String username;

    @NotEmpty
    String name;

    Boolean isValid;

    @NotEmpty
    @Email
    String mail;

    @NotEmpty
    @Size(min = 5, max = 10)
    @Column(nullable = false)
    String phone;


    Set<Authority> authorities = new HashSet<>();

    public PersonForm(Person person) {
        this.id = person.id;
        this.username = person.username;
        this.name = person.name;
        this.mail = person.mail;
        this.phone = person.phone;
        this.authorities = person.authorities;
    }
}
