package wsb.demo.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Service
public class PersonService {


    private final AuthorityRepository authorityRepository;
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${my.admin.username}")
    private String myAdminUsername;

    @Value("${my.admin.password}")
    private String myAdminPassword;

    public PersonService(AuthorityRepository authorityRepository, PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorityRepository = authorityRepository;
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void prepareAdminUser() {
        if (personRepository.findByUsername(myAdminUsername) != null) {
            System.out.println("Użytkownik " + myAdminUsername + " już istnieje. Przerywamy tworzenie.");
            return;
        }

        System.out.println("Tworzymy administratora: " + myAdminUsername + "...");
        Person person = new Person(myAdminUsername, myAdminPassword, "Admin", true, "admin@admin.pl", "777666111");
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        person.setAuthorities(new HashSet<>(authorities));
        savePerson(person);
    }


    protected void savePerson(Person person) {
        String hashedPassword = bCryptPasswordEncoder.encode(person.password);
        person.setPassword(hashedPassword);
        personRepository.save(person);
    }


    public void updatePassword(PasswordForm passwordForm) {
        Person person = personRepository.findById(passwordForm.id).orElse(null);
        String pass = bCryptPasswordEncoder.encode(passwordForm.password);
        person.setPassword(pass);
        personRepository.save(person);
    }

    public void savePerson(PersonForm personForm) {
        Person person = personRepository.findById(personForm.id).orElse(null);
        person.username = personForm.username;
        person.name = personForm.name;
        person.mail = personForm.mail;
        if (personForm.authorities.size() != 0){
            person.authorities = personForm.authorities;}
//        person.authorities = personForm.authorities;
        personRepository.save(person);
    }

    public void softDelete(Person person) {
        person.setEnable(false);
        personRepository.save(person);
    }

    List<Person> findAllUsers() {
        return personRepository.findAll();
    }

    protected List<Authority> findAuthorities() {
        return (List<Authority>) authorityRepository.findAll();
    }

    protected Person editPerson(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id użytkownika: " + id));
    }

    public Person currentUser(String username){
        return personRepository.findByUsername(username);
    }

}
