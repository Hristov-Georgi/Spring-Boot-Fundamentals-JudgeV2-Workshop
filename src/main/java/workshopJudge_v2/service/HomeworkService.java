package workshopJudge_v2.service;


import workshopJudge_v2.model.binding.HomeworkAddBindingModel;
import workshopJudge_v2.model.entity.Homework;
import workshopJudge_v2.model.serviceModel.HomeworkServiceModel;

public interface HomeworkService {

    void addHomework(HomeworkAddBindingModel homeworkAddBindingModel);

    boolean isExerciseExpired(String exerciseName);

    HomeworkServiceModel getHomeworkForCheck(String currentUsername);

    Homework findById(Long id);
}
