package workshopJudge_v2.model.view;

import java.util.List;

public class UserProfileViewModel {

    private String username;

    private List<String> homeworks;

    private String email;

    private String git;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<String> homeworks) {
        this.homeworks = homeworks;
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
