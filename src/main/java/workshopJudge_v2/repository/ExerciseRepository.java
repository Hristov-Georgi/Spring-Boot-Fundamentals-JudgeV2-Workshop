package workshopJudge_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshopJudge_v2.model.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
