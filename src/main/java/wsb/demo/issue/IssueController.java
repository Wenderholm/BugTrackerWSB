package wsb.demo.issue;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issue")
public class IssueController {

    @GetMapping("/")
    @Secured("ROLE_MANAGE_PROJECT")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("issue/index");
        return modelAndView;
    }
}
