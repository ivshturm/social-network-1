package ru.sberbank.project.controller.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sberbank.project.model.Comment;
import ru.sberbank.project.model.CommentTo;
import ru.sberbank.project.security.AuthorizedUser;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.service.ArticleService;
import ru.sberbank.project.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.sberbank.project.util.ValidationUtil.assureIdConsistent;
import static ru.sberbank.project.util.ValidationUtil.checkNew;

public abstract class AbstractArticleController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleService service;

    public Article get(int id) {
        log.info("get article {} for user {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete article {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Article> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public List<Article> getAll(int id) {
        log.info("getAll for user {}", id);
        return service.getAll(id);
    }

    public Article create(Article article) {
        int userId = AuthorizedUser.id();
        checkNew(article);
        log.info("create {} for user {}", article, userId);
        return service.create(article, userId);
    }

    public void update(Article article, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(article, id);
        log.info("update {} for user {}", article, userId);
        service.update(article, userId);
    }


    public Comment saveComment(Comment comment) {
        int userId = AuthorizedUser.id();
        log.info("saveComment {} for user {}", comment, userId);
        return service.saveComment(comment, userId);
    }

    public void deleteComment(int id) {
        int userId = AuthorizedUser.id();
        log.info("deleteComment {} for user {}", id, userId);
        service.deleteComment(id, userId);
    }

    public List<CommentTo> getAllCommentsForArticle(int articleId) {
        log.info("getAllCommentsForArticle {}", articleId);
        return service.getAllCommentsForArticleById(articleId);
    }
}