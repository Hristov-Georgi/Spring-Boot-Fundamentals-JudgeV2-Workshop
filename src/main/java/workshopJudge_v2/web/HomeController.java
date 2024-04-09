package workshopJudge_v2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import workshopJudge_v2.security.CurrentUser;
import workshopJudge_v2.service.CommentService;
import workshopJudge_v2.service.ExerciseService;
import workshopJudge_v2.service.UserService;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ExerciseService exerciseService;
    private final CommentService commentService;
    private final UserService userService;


    public HomeController(CurrentUser currentUser, ExerciseService exerciseService, CommentService commentService, UserService userService) {
        this.currentUser =  currentUser;
        this.exerciseService = exerciseService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if(this.currentUser.isAnonymous()) {
            return "index";
        }

        model.addAttribute("exercises", this.exerciseService.getExercisesNames());

        model.addAttribute("topStudents", this.userService.findTopStudents());

        model.addAttribute("averageScore", this.commentService.findAverageScore());

        model.addAttribute("usersCount", this.userService.findUsersCount());

        model.addAttribute("scoresMap", this.commentService.findScoreMap());

        return "home";

    }



}

