package wsb.demo.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PersonForm {


    Long id;

    @NotEmpty
    String username;

    @NotEmpty
    String name;

    @NotEmpty
    String mail;

    @NotEmpty
    @Size(min =5, max=10)
    @Column(nullable = false)
    String phone;

    public PersonForm(Person person) {
        this.id = person.id;
        this.username = person.username;
        this.name = person.name;
        this.mail = person.mail;
        this.phone = person.phone;
    }

}
