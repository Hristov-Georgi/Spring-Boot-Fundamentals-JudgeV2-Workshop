package workshopJudge_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workshopJudge_v2.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {


    @Query("SELECT u.username FROM UserEntity u")
    List<String> findAllUsernames();

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u.username FROM UserEntity u" +
            " ORDER BY SIZE(u.homeworks) DESC")
    List<String> findTopStudents();

}
