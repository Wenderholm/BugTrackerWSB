package wsb.demo.validators;

import wsb.demo.auth.PasswordForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEditPasswordsValidator implements ConstraintValidator<ValidPasswords, PasswordForm> {

    @Override
    public void initialize(ValidPasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PasswordForm passwordForm, ConstraintValidatorContext ctx) {
        if (passwordForm.getPassword() == null || passwordForm.getPassword().equals("")) {
            if (passwordForm.getId() == null) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("password")
                        .addConstraintViolation();

                return false;
            }
            return true;
        }
        boolean passwordsAreValid = passwordForm.getPassword().equals(passwordForm.getPasswordAgain());
        if (passwordsAreValid) {
            return true;
        } else {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("passwordAgain")
                    .addConstraintViolation();

            return false;
        }
    }

}
