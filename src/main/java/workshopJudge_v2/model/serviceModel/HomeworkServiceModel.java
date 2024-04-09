package workshopJudge_v2.model.serviceModel;

import workshopJudge_v2.model.entity.Comment;
import workshopJudge_v2.model.entity.Exercise;
import workshopJudge_v2.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class HomeworkServiceModel {

    private Long id;

    private LocalDateTime addedOn;

    private String gitAddress;

    private User author;

    private Exercise exercise;

    private List<Comment> comments;

    public HomeworkServiceModel() {
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
