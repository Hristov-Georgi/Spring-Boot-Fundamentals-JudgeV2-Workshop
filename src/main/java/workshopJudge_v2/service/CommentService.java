package workshopJudge_v2.service;

import workshopJudge_v2.model.binding.CommentAddBindingModel;
import java.util.Map;

public interface CommentService {

    void add(CommentAddBindingModel commentAddBindingModel, String username);

    Double findAverageScore();

    Map<Integer, Integer> findScoreMap();
}
