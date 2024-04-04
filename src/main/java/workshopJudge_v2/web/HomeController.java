package workshopJudge_v2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import workshopJudge_v2.security.CurrentUser;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser =  currentUser;
    }

    @GetMapping("/")
    public String index() {
        if(this.currentUser.isAnonymous()) {
            return "index";
        }

        return "home";

    }



}

