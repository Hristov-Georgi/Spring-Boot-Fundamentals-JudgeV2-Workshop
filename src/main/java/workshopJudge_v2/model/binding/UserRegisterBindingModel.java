package workshopJudge_v2.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserRegisterBindingModel {

    @Length(min = 2, message = "username length must be minimum two characters!")
    @NotNull
    private String username;

    @Length(min = 3, message = "password length must be minimum three characters!")
    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @Email(message = "enter valid email address!")
    @NotNull
    private String email;

    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+", message = "enter valid github address!")
    @NotNull
    private String git;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
