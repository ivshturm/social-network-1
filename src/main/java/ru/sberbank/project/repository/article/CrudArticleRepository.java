package ru.sberbank.project.repository.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.project.model.Article;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface CrudArticleRepository extends JpaRepository<Article, Integer> {
    @Modifying
    @Query("DELETE FROM Article a WHERE a.id=:id AND a.userId=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Article save(Article item);

    @Query("SELECT a FROM Article a WHERE a.userId=:userId ORDER BY a.dateTime DESC")
    List<Article> getAll(@Param("userId") int userId);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT a from Article a WHERE a.userId=:userId AND a.dateTime BETWEEN :startDate AND :endDate ORDER BY a.dateTime DESC")
    List<Article> getBetween(
            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
