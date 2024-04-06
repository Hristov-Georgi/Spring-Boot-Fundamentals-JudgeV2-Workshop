package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.binding.HomeworkAddBindingModel;
import workshopJudge_v2.model.entity.Exercise;
import workshopJudge_v2.model.entity.Homework;
import workshopJudge_v2.model.entity.User;
import workshopJudge_v2.model.serviceModel.HomeworkServiceModel;
import workshopJudge_v2.repository.ExerciseRepository;
import workshopJudge_v2.repository.HomeworkRepository;
import workshopJudge_v2.repository.UserRepository;
import workshopJudge_v2.security.CurrentUser;
import workshopJudge_v2.service.HomeworkService;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkServiceImpl(CurrentUser currentUser, UserRepository userRepository,
                               ExerciseRepository exerciseRepository, ModelMapper modelMapper, HomeworkRepository homeworkRepository) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public void addHomework(HomeworkAddBindingModel homeworkAddBindingModel) {

        User currentUser = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        Exercise exercise = this.exerciseRepository.findByName(homeworkAddBindingModel.getExercise()).get();

        HomeworkServiceModel homeworkServiceModel = new HomeworkServiceModel();
        homeworkServiceModel.setAddedOn();
        homeworkServiceModel.setAuthor(currentUser);
        homeworkServiceModel.setGitAddress(homeworkAddBindingModel.getGitAddress());
        homeworkServiceModel.setExercise(exercise);

        this.homeworkRepository.save(this.modelMapper.map(homeworkServiceModel, Homework.class));
    }

    @Override
    public boolean isExerciseExpired(String exerciseName) {

        Exercise exercise = this.exerciseRepository.findByName(exerciseName).get();

        return exercise.getDueDate().isBefore(LocalDateTime.now());
    }
}
