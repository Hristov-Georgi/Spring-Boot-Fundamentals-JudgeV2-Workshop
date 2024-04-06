package workshopJudge_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workshopJudge_v2.model.entity.Exercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e.name FROM Exercise e ORDER BY e.name")
    List<String> findAllByName();

    Optional<Exercise> findByName(String exName);
}
