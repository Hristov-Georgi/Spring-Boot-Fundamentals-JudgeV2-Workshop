package workshopJudge_v2.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import workshopJudge_v2.service.CommentService;
import workshopJudge_v2.service.ExerciseService;
import workshopJudge_v2.service.HomeworkService;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService,
                              ModelMapper modelMapper,
                              CommentService commentService) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

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
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails userDetails) {

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

        this.homeworkService.addHomework(homeworkAddBindingModel, userDetails.getUsername());

        return "redirect:/";
    }

    @GetMapping("/check")
    public String checkHomework(Model model,@AuthenticationPrincipal UserDetails userDetails) {

        if(!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        HomeworkViewModel homeworkViewModel = this.modelMapper
                .map(this.homeworkService.getHomeworkForCheck(userDetails.getUsername()), HomeworkViewModel.class);

        model.addAttribute("homework", homeworkViewModel);

        return "homework-check";
    }

    @PostMapping("/check")
    public String checkHomeworkConfirm(@Valid CommentAddBindingModel commentAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes,
                                       @AuthenticationPrincipal UserDetails userDetails) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel",
                            bindingResult);

            return "redirect:check";
        }

        this.commentService.add(commentAddBindingModel, userDetails.getUsername());


        return "redirect:/";
    }



}
