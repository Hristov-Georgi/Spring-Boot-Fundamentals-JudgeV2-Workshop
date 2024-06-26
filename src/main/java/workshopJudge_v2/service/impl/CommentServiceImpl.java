package workshopJudge_v2.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshopJudge_v2.model.binding.CommentAddBindingModel;
import workshopJudge_v2.model.entity.Comment;
import workshopJudge_v2.model.serviceModel.CommentServiceModel;
import workshopJudge_v2.repository.CommentRepository;
import workshopJudge_v2.service.CommentService;
import workshopJudge_v2.service.HomeworkService;
import workshopJudge_v2.service.UserEntityService;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserEntityService userEntityService;
    private final HomeworkService homeworkService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserEntityService userService,
                              HomeworkService homeworkService,
                              ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userEntityService = userService;
        this.homeworkService = homeworkService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void add(CommentAddBindingModel commentAddBindingModel, String username) {

        CommentServiceModel commentServiceModel = this.modelMapper
                .map(commentAddBindingModel, CommentServiceModel.class);

        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
        comment.setAuthor(this.userEntityService.findByUsername(username));
        comment.setHomework(this.homeworkService.findById(commentAddBindingModel.getHomeworkId()));

        this.commentRepository.save(comment);
    }

    @Override
    public Double findAverageScore() {

        return this.commentRepository.findAverageScore();

    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer> scoreMap = initializeMap();

        this.commentRepository.findAll().forEach(comment -> {
            int score = comment.getScore();
            scoreMap.put(score, scoreMap.get(score) + 1);
        });

        return scoreMap;
    }

    private Map<Integer, Integer> initializeMap() {
        Map<Integer, Integer> initialMap = new HashMap<>();

        for (int i = 2; i <= 6 ; i++) {
            initialMap.put(i, 0);
        }

        return initialMap;
    }
}
