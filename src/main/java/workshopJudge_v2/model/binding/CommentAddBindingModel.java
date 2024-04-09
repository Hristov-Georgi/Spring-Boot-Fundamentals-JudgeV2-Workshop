package workshopJudge_v2.model.binding;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class CommentAddBindingModel {

    @NotNull
    @Min(value = 2, message = "Score value must be equal or higher to 2")
    @Max(value = 6, message = "Score value must be equal or lower to 6 ")
    private Integer score;

    @NotBlank
    @Length(min = 3, message = "Text content should be at least 3 symbols")
    private String textContent;

    private Long homeworkId;

    public CommentAddBindingModel() {
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

    public Long getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        this.homeworkId = homeworkId;
    }
}
