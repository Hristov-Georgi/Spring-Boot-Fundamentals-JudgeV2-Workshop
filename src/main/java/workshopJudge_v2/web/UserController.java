package workshopJudge_v2.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import workshopJudge_v2.model.binding.UserRegisterBindingModel;
import workshopJudge_v2.service.UserEntityService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserEntityService userEntityService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserController(UserEntityService userService, ModelMapper modelMapper) {
        this.userEntityService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(@ModelAttribute("username")
                                         String username,
                                  RedirectAttributes redirectAttributes) {

            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        if(!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                        bindingResult);

            return "redirect:register";
        }

        this.userEntityService.registerUser(userRegisterBindingModel);

        return "login";
    }

    @GetMapping("/profile/{name}")
    public String profile(@PathVariable String name, Model model) {

        model.addAttribute("userProfile", this.userEntityService.findProfileData(name));

        return "profile";
    }


}
