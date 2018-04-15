package ru.sberbank.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.repository.article.ArticleRepository;
import ru.sberbank.project.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.sberbank.project.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String REMOTE_SERVICE = "remote";
    private static final String LOCAL_REPOSITORY = "local";

    private final ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(@Qualifier(LOCAL_REPOSITORY) ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Article get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Article> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Article> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Article update(Article article, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(article, userId), article.getId());
    }

    @Override
    public Article create(Article article, int userId) {
        Assert.notNull(article, "Article must not be null");
        article.setDateTime(LocalDateTime.now());
        return repository.save(article, userId);
    }
}
