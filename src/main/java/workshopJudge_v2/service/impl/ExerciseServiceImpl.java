package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.entity.Exercise;
import workshopJudge_v2.model.serviceModel.ExerciseServiceModel;
import workshopJudge_v2.repository.ExerciseRepository;
import workshopJudge_v2.service.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveExercise(ExerciseServiceModel exerciseServiceModel) {
        Exercise exercise = this.modelMapper.map(exerciseServiceModel, Exercise.class);

        this.exerciseRepository.save(exercise);
    }
}
