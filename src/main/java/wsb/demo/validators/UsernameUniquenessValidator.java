package wsb.demo.validators;

import wsb.demo.auth.PersonRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniquenessValidator implements ConstraintValidator<UniqueUsername, String>{

    private final PersonRepository personRepository;

    public UsernameUniquenessValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username != null && personRepository.findByUsername(username) == null;
    }
}
