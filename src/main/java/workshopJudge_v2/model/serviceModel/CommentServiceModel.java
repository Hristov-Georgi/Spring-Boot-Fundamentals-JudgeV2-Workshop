package workshopJudge_v2.model.serviceModel;


import workshopJudge_v2.model.entity.Homework;

public class CommentServiceModel {

    private Integer score;

    private String textContent;

    private UserServiceModel author;

    private Homework homework;

    public CommentServiceModel() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
