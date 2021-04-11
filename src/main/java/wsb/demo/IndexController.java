package wsb.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
//
//    @GetMapping("/")
//    public String index(@RequestParam(name="id") String id, Model model){
//        model.addAttribute("name", "myszojelen");
//        double number = 12+23+31;
//        model.addAttribute("number", number );
//        model.addAttribute("id",id);
//        return "index";
//    }
//
//    @GetMapping("/contact")
//    public String contact(){
//        return "contact";
//    }
    private final static String INDEX_VIEW_NAME = "index";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isAdmin",true);
        return INDEX_VIEW_NAME;
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }



}
