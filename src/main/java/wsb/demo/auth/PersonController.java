package wsb.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private PersonService personService;
    private PersonRepository personRepository;
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
    @Secured("ROLE_MANAGE_USER")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("people/create");
        modelAndView.addObject("person", new Person());
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        modelAndView.addObject("authorities", authorities);
        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_MANAGE_USER")
    ModelAndView createNewUser(@Valid @ModelAttribute Person person, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        modelAndView.addObject("authorities", authorities);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("people/create");
            return modelAndView;
        }

        personService.savePerson(person);
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured({"ROLE_MANAGE_USER","ROLE_OWNER"})
    ModelAndView showUpdateForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("people/show");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id użytkownika: " + id));
        modelAndView.addObject("personForm", new PersonForm(person));
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        modelAndView.addObject("authorities", authorities);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    @Secured({"ROLE_MANAGE_USER","ROLE_OWNER"})
    ModelAndView updateUser(@PathVariable("id") long id, @Valid PersonForm personForm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("people/show");
        modelAndView.addObject("personForm", personForm);
        if (result.hasErrors()) {
            personForm.setId(id);
            return modelAndView;
        }
        personService.savePerson(personForm);
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }

    @GetMapping("/user_home")
    ModelAndView viewUserHome(@AuthenticationPrincipal Person person, Principal principal){
        ModelAndView modelAndView = new ModelAndView("user_home");
        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        modelAndView.addObject("authorities", authorities);
        modelAndView.addObject("person", personService.currentUser(principal.getName()));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @Secured("ROLE_MANAGE_USER")
    ModelAndView deleteUser(@PathVariable("id") long id, Person person) {
        ModelAndView modelAndView = new ModelAndView();
        personService.softDelete(personService.editPerson(id));
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }

//    @GetMapping("/{id}")
//    @Secured("ROLE_USERS_TAB")
//    ModelAndView showUserDetails(@ModelAttribute @PathVariable("id") Long id) {
//        ModelAndView modelAndView = new ModelAndView("account/index");
//        modelAndView.addObject("allAuthorities", personService.findAuthorities());
//        modelAndView.addObject("person", personService.editPerson(id));
//        return modelAndView;
//    }

    @GetMapping("/editPassword/{id}")
    @Secured({"ROLE_MANAGE_USER","ROLE_OWNER"})
    ModelAndView showUpdatePassForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("people/password");
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe Id użytkownika: " + id));
        PasswordForm passwordForm = new PasswordForm(person);
        passwordForm.setId(id);
        modelAndView.addObject("passwordForm", passwordForm);
        return modelAndView;
    }

    @PostMapping("/updatePassword/{id}")
    @Secured({"ROLE_MANAGE_USER","ROLE_OWNER"})
    ModelAndView updatePassword(@PathVariable("id") long id, @Valid PasswordForm passwordForm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("people/password");
        modelAndView.addObject("passwordForm", passwordForm);
        if (result.hasErrors()) {
            passwordForm.setId(id);
            return modelAndView;
        }
        personService.updatePassword(passwordForm);

        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }





}
