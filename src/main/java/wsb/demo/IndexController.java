package wsb.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    private final static String INDEX_VIEW_NAME = "index";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isAdmin",true);
//        model.add("person", Person);
        return INDEX_VIEW_NAME;
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}
