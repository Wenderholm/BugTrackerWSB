package wsb.demo.project;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.demo.auth.Authority;
import wsb.demo.auth.Person;

import javax.validation.Valid;
import java.util.List;

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
    @GetMapping("/edit/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView showUpdateForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("project/update-project");
        modelAndView.addObject("project", projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView updateUser(@PathVariable("id") long id, @Valid Project project, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("project/update-project");
        modelAndView.addObject("project",project);
        if (result.hasErrors()) {
            project.setId(id);
            return modelAndView;
        }
        projectRepository.save(project);
        modelAndView.setViewName("redirect:/project/");
        return modelAndView;
    }

}
