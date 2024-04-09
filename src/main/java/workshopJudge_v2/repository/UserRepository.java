package workshopJudge_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workshopJudge_v2.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u.username FROM User u")
    List<String> findAllUsernames();

    Optional<User> findByUsername(String username);

    @Query("SELECT u.username FROM User u" +
            " ORDER BY SIZE(u.homeworks) DESC")
    List<String> findTopStudents();

}
