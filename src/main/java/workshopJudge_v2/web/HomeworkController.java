package workshopJudge_v2.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshopJudge_v2.model.binding.CommentAddBindingModel;
import workshopJudge_v2.model.binding.HomeworkAddBindingModel;
import workshopJudge_v2.model.view.HomeworkViewModel;
import workshopJudge_v2.security.CurrentUser;
import workshopJudge_v2.service.CommentService;
import workshopJudge_v2.service.ExerciseService;
import workshopJudge_v2.service.HomeworkService;

import javax.print.attribute.standard.PresentationDirection;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService,
                              ModelMapper modelMapper, CurrentUser currentUser,
                              CommentService commentService) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if(currentUser.isAnonymous()) {
            return "redirect:/";
        }

        model.addAttribute("exName", this.exerciseService.getExercisesNames());

        if(!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isExpired", false);
        }

        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute HomeworkAddBindingModel homeworkAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);

            return "redirect:add";
        }

        boolean isExpired = this.homeworkService.isExerciseExpired(homeworkAddBindingModel.getExercise());

        if(isExpired) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("isExpired", true);

            return "redirect:add";
        }

        this.homeworkService.addHomework(homeworkAddBindingModel);

        return "redirect:/";
    }

    @GetMapping("/check")
    public String checkHomework(Model model) {

        if(currentUser.isAnonymous()) {
            return "redirect:/";
        }

        if(!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        HomeworkViewModel homeworkViewModel = this.modelMapper
                .map(this.homeworkService.getHomeworkForCheck(this.currentUser.getUsername()), HomeworkViewModel.class);

        model.addAttribute("homework", homeworkViewModel);

        return "homework-check";
    }

    @PostMapping("/check")
    public String checkHomeworkConfirm(@Valid CommentAddBindingModel commentAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel",
                            bindingResult);

            return "redirect:check";
        }

        this.commentService.add(commentAddBindingModel);


        return "redirect:/";
    }



}
