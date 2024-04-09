package workshopJudge_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workshopJudge_v2.model.entity.Homework;


@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    @Query("SELECT h FROM Homework h" +
            " WHERE h.author.username <> :name" +
            " ORDER BY SIZE(h.comments) ASC" +
            " LIMIT 1")
    Homework findTop1ByOrderByComments(@Param("name") String name);
}
