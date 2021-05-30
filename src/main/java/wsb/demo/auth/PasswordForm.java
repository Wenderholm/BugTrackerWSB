package wsb.demo.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class PasswordForm {

    Long id;

    @NotEmpty
    String password;

    @NotEmpty
    String passwordAgain;

    boolean isValid;

    @AssertTrue(message = "passVeryfi field should be equal than pass field")
    public boolean isValid(){
        if (password == null)
            return false;
        if (passwordAgain == null)
            return false;
        else
            return this.password.equals(this.passwordAgain);
    }

    public PasswordForm(Person person){
        this.password = person.password;
        this.passwordAgain = person.repeatedPassword;
    }

}
