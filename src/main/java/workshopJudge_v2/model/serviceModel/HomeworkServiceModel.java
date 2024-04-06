package workshopJudge_v2.model.serviceModel;

import workshopJudge_v2.model.entity.Exercise;
import workshopJudge_v2.model.entity.User;

import java.time.LocalDateTime;

public class HomeworkServiceModel {

    private LocalDateTime addedOn;

    private String gitAddress;

    private User author;

    private Exercise exercise;

    public HomeworkServiceModel() {
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn() {
        this.addedOn = LocalDateTime.now();
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
