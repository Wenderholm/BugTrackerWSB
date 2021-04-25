package wsb.demo.project;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wsb.demo.auth.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_MANAGE_PROJECT")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("project/index");
        modelAndView.addObject("project",projectRepository.findByEnable(true));
        return modelAndView;
    }
    @GetMapping("/create")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("project", new Project());
        return modelAndView;
    }
    @PostMapping(value = "/save")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView createNewUser(@Valid @ModelAttribute Project project) {
        ModelAndView modelAndView = new ModelAndView();
        projectRepository.save(project);
        modelAndView.setViewName("redirect:/project/");
        return modelAndView;
    }

}
