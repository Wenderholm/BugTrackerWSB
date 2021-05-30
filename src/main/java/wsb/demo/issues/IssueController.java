package wsb.demo.issues;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.demo.auth.PersonRepository;
import wsb.demo.enums.Priority;
import wsb.demo.enums.State;
import wsb.demo.enums.Type;
import wsb.demo.mial.Mail;
import wsb.demo.mial.MailService;
import wsb.demo.project.ProjectRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/issue")
public class IssueController {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;
    private final IssueService issueService;
    private final MailService mailService;


    public IssueController(IssueRepository issueRepository, ProjectRepository projectRepository, PersonRepository personRepository, IssueService issueService, MailService mailService) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.issueService = issueService;
        this.mailService = mailService;
    }

    @GetMapping
    ModelAndView index(@ModelAttribute IssueFilter issueFilter) {
        ModelAndView modelAndView = new ModelAndView("issue/index");
        modelAndView.addObject("issues", issueRepository.findAll(issueFilter.buildQuery()));
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());
        modelAndView.addObject("states", State.values());
        modelAndView.addObject("filter", issueFilter);
        return modelAndView;
    }
    @GetMapping("/create")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issue/issue-create");
        modelAndView.addObject("projects", projectRepository.findByEnable(true));
        modelAndView.addObject("people", personRepository.findByEnable(true));
        modelAndView.addObject("states", State.values());
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());
        modelAndView.addObject("issue", new Issue());
        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView createNewProject(@ModelAttribute @Valid Issue issue, BindingResult bindingResult, Mail mail) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projects", projectRepository.findByEnable(true));
        modelAndView.addObject("people", personRepository.findByEnable(true));
        modelAndView.addObject("states", State.values());
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("issue/issue-create");
            return modelAndView;
        }
        mailService.sendToAssignee(issue);

        issueRepository.save(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView showUpdateForm(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("issue/update-issue");
        modelAndView.addObject("issue", issueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        modelAndView.addObject("projects", projectRepository.findByEnable(true));
        modelAndView.addObject("people", personRepository.findByEnable(true));
        modelAndView.addObject("states", State.values());
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView updateUser(@PathVariable("id") long id, @Valid Issue issue, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("issue/update-issue");
        modelAndView.addObject("issue", issue);
        modelAndView.addObject("projects", projectRepository.findByEnable(true));
        modelAndView.addObject("people", personRepository.findByEnable(true));
        modelAndView.addObject("states", State.values());
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());
        if (result.hasErrors()) {
            issue.setId(id);
            return modelAndView;
        }

        issueRepository.save(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    @Secured("ROLE_CREATE_USER")
    ModelAndView deleteIssue(@PathVariable("id") long id, Issue issue) {
        ModelAndView modelAndView = new ModelAndView();
        issueService.softDelete(issueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }
}
