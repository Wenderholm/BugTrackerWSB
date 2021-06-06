package wsb.demo.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wsb.demo.auth.Person;
import wsb.demo.auth.PersonService;

@RestController
@RequestMapping("/test")
public class TestController {
    private final PersonService personService;

    public TestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Person testMetoda(){
        Long id = 0l;
        var person = personService.findPersonById(id).orElse(new Person());
        return person;
    }
}
