package wsb.demo.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wsb.demo.validators.ValidPasswords;

import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ValidPasswords
public class PasswordForm {

    Long id;

    @NotEmpty
    String password;

    @Transient
    String passwordAgain;

    boolean isValid;

    public PasswordForm(Person person) {
        this.password = person.password;
        this.passwordAgain = person.repeatedPassword;
    }

}
