package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.binding.HomeworkAddBindingModel;
import workshopJudge_v2.model.entity.Exercise;
import workshopJudge_v2.model.entity.Homework;
import workshopJudge_v2.model.entity.UserEntity;
import workshopJudge_v2.model.serviceModel.HomeworkServiceModel;
import workshopJudge_v2.repository.ExerciseRepository;
import workshopJudge_v2.repository.HomeworkRepository;
import workshopJudge_v2.repository.UserEntityRepository;
import workshopJudge_v2.service.HomeworkService;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final UserEntityRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkServiceImpl(UserEntityRepository userRepository,
                               ExerciseRepository exerciseRepository, ModelMapper modelMapper, HomeworkRepository homeworkRepository) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public void addHomework(HomeworkAddBindingModel homeworkAddBindingModel,
                            String username) {

        UserEntity currentUser = this.userRepository.findByUsername(username).get();
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

    @Override
    public HomeworkServiceModel getHomeworkForCheck(String currentUsername) {

        return this.modelMapper
                .map(this.homeworkRepository.findTop1ByOrderByComments(currentUsername),
                        HomeworkServiceModel.class);

    }

    @Override
    public Homework findById(Long id) {

        return this.homeworkRepository.findById(id).get();

    }
}
