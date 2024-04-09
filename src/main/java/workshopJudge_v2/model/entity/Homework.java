package workshopJudge_v2.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "added_on", nullable = false, updatable = false)
    private LocalDateTime addedOn;

    @Column(name = "git_address", nullable = false)
    private String gitAddress;

    @ManyToOne
    private User author;

    @ManyToOne
    private Exercise exercise;

    @OneToMany(mappedBy = "homework", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Homework() {
        this.setAddedOn();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
