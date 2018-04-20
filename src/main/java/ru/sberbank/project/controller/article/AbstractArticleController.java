package ru.sberbank.project.controller.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sberbank.project.model.*;
import ru.sberbank.project.security.AuthorizedUser;
import ru.sberbank.project.service.ArticleService;
import ru.sberbank.project.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static ru.sberbank.project.util.ValidationUtil.assureIdConsistent;
import static ru.sberbank.project.util.ValidationUtil.checkNew;

public abstract class AbstractArticleController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    public Article get(int id) {
        log.info("get article {} for user {}", id);
        return articleService.get(id);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete article {} for user {}", id, userId);
        articleService.delete(id, userId);
    }

    public List<Article> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return articleService.getAll(userId);
    }

    public List<Article> getAll(int id) {
        log.info("getAll for user {}", id);
        return articleService.getAll(id);
    }

    public Article create(Article article) {
        int userId = AuthorizedUser.id();
        checkNew(article);
        log.info("create {} for user {}", article, userId);
        return articleService.create(article, userId);
    }

    public void update(Article article, int id) {
        int userId = AuthorizedUser.id();
        assureIdConsistent(article, id);
        log.info("update {} for user {}", article, userId);
        articleService.update(article, userId);
    }


    public Comment saveComment(Comment comment) {
        int userId = AuthorizedUser.id();
        log.info("saveComment {} for user {}", comment, userId);
        return articleService.saveComment(comment, userId);
    }

    public void deleteComment(int id) {
        int userId = AuthorizedUser.id();
        log.info("deleteComment {} for user {}", id, userId);
        articleService.deleteComment(id, userId);
    }

    public List<CommentTo> getAllCommentsForArticle(int articleId) {
        log.info("getAllCommentsForArticle {}", articleId);
        return articleService.getAllCommentsForArticleById(articleId);
    }

    public List<ArticleTo> getNews() {
        int userId = AuthorizedUser.id();
        return articleService.getNews(userService.getAllFollowingByUserId(userId));
    }
}