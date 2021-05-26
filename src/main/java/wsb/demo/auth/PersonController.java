package wsb.demo.auth;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final PersonRepository personRepository;
    private final AuthorityRepository authorityRepository;

    public PersonController(PersonService personService, PersonRepository personRepository, AuthorityRepository authorityRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("people/index");
        modelAndView.addObject("people", personRepository.findByEnable(true));
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_CREATE_USER")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("people/create");
        modelAndView.addObject("person", new Person());
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        modelAndView.addObject("allAuthorities", authorities);
//        modelAndView.addObject("allAuthorities", authorityRepository.findAll());

        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_CREATE_USER")
    ModelAndView createNewUser(@Valid @ModelAttribute Person person, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("people/create");
            List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
            modelAndView.addObject("allAuthorities", authorities);
//            modelAndView.addObject("allAuthorities", authorityRepository.findAll());
            return modelAndView;
        }
        personService.savePerson(person);
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_CREATE_USER")
    ModelAndView showUpdateForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("people/update-user");
        modelAndView.addObject("person", personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        modelAndView.addObject("allAuthorities", authorities);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    @Secured("ROLE_CREATE_USER")
    ModelAndView updateUser(@PathVariable("id") long id, @Valid Person person, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("people/update-user");
        modelAndView.addObject("person",person);
        if (result.hasErrors()) {
            person.setId(id);
            return modelAndView;
        }
        personService.savePerson(person);
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @Secured("ROLE_CREATE_USER")
    ModelAndView deleteUser(@PathVariable("id") long id, Person person) {
        ModelAndView modelAndView = new ModelAndView();
        personService.softDelete(personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }
}
