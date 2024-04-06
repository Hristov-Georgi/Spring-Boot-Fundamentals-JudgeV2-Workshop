package workshopJudge_v2.service;


import workshopJudge_v2.model.binding.HomeworkAddBindingModel;

public interface HomeworkService {

    void addHomework(HomeworkAddBindingModel homeworkAddBindingModel);

    boolean isExerciseExpired(String exerciseName);
}
