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
import workshopJudge_v2.model.binding.ExerciseAddBindingModel;
import workshopJudge_v2.model.entity.Exercise;
import workshopJudge_v2.model.serviceModel.ExerciseServiceModel;
import workshopJudge_v2.service.ExerciseService;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;


    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addExercise(Model model) {
        if(!model.containsAttribute("exerciseAddBindingModel")) {
            model.addAttribute("exerciseAddBindingModel", new ExerciseAddBindingModel());

        }

        return "exercise-add";
    }

    @PostMapping("/add")
    public String addExerciseConfirm(@Valid @ModelAttribute ExerciseAddBindingModel exerciseAddBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("exerciseAddBindingModel", exerciseAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.exerciseAddBindingModel"
                            ,bindingResult);

            return "redirect:add";
        }

        ExerciseServiceModel model = this.modelMapper.map(exerciseAddBindingModel, ExerciseServiceModel.class);
        this.exerciseService.saveExercise(model);

        return "redirect:/";
    }




}
