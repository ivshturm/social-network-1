package ru.sberbank.project.repository.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository("remote")
public class RemoteServiceArticleRepository implements ArticleRepository {

    @Autowired
    private ArticleFeignClient articleFeignClient;

    @Override
    public Article save(Article article, int userId) {
        if (!article.isNew() && get(article.getId(), userId) == null) {
            return null;
        }
        article.setUserId(userId);
        return articleFeignClient.save(article, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        return Objects.nonNull(articleFeignClient.delete(id, userId));
    }

    @Override
    public Article get(int id, int userId) {
        return articleFeignClient.get(id, userId);
    }

    @Override
    public List<Article> getAll(int userId) {
        return articleFeignClient.getAll(userId);
    }

    @Override
    public List<Article> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return getAll(userId).stream()
                .filter(article -> DateTimeUtil.isBetween(article.getDateTime(), startDate, endDate))
                .collect(Collectors.toList());
    }
}
