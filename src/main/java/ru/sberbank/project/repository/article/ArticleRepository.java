package ru.sberbank.project.repository.article;

import ru.sberbank.project.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository {

    Article save(Article article, int userId);

    boolean delete(int id, int userId);

    Article get(int id);

    List<Article> getAll(int userId);

    List<Article> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
