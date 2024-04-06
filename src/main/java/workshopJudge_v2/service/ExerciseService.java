package workshopJudge_v2.service;

import workshopJudge_v2.model.serviceModel.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {

    void saveExercise(ExerciseServiceModel exerciseServiceModel);

    List<String> getExercisesNames();
}
