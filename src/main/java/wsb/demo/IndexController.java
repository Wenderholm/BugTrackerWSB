package wsb.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import wsb.demo.auth.PersonRepository;

@Controller
public class IndexController {
    private final static String INDEX_VIEW_NAME = "index";
    private final PersonRepository personRepository;

    public IndexController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(INDEX_VIEW_NAME);
        modelAndView.addObject("people", personRepository.findByUsername("aaa"));
        return modelAndView;
//        model.addAttribute("isAdmin",true);
//        model.add("person", Person);
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
