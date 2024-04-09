package workshopJudge_v2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import workshopJudge_v2.model.binding.RoleAddBindingModel;
import workshopJudge_v2.security.CurrentUser;
import workshopJudge_v2.service.UserService;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public RoleController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/add")
    public String addRole(Model model) {

        if(this.currentUser.isAnonymous() || !currentUser.isAdmin()) {
            return "redirect:/";
        }

        model.addAttribute("usernames", this.userService.getAllUsernames());

        return "role-add";
    }

    @PostMapping("/add")
    public String addRoleConfirm(@ModelAttribute RoleAddBindingModel roleAddBindingModel) {

        this.userService.changeUserRole(roleAddBindingModel);

        return "redirect:/";
    }
}
