package ru.sberbank.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.model.Comment;
import ru.sberbank.project.model.CommentTo;
import ru.sberbank.project.model.User;
import ru.sberbank.project.repository.article.ArticleRepository;
import ru.sberbank.project.repository.comment.CommentRepository;
import ru.sberbank.project.repository.user.UserRepository;
import ru.sberbank.project.util.UserUtil;
import ru.sberbank.project.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sberbank.project.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final String REMOTE_SERVICE = "remote";
    private static final String LOCAL_REPOSITORY = "local";

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(@Qualifier(LOCAL_REPOSITORY) ArticleRepository articleRepository,
                              CommentRepository commentRepository,
                              UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Article get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(articleRepository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(articleRepository.delete(id, userId), id);
    }

    @Override
    public List<Article> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return articleRepository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Article> getAll(int userId) {
        return articleRepository.getAll(userId);
    }

    @Override
    public Article update(Article article, int userId) throws NotFoundException {
        return checkNotFoundWithId(articleRepository.save(article, userId), article.getId());
    }

    @Override
    public Article create(Article article, int userId) {
        Assert.notNull(article, "Article must not be null");
        article.setDateTime(LocalDateTime.now());
        return articleRepository.save(article, userId);
    }

    @Override
    public Comment saveComment(Comment comment, int userId) {
        comment.setUserId(userId);
        comment.setDateTime(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(int id, int userId) {
        return commentRepository.delete(id, userId);
    }

    @Override
    public List<CommentTo> getAllCommentsForArticleById(int id) {
        return commentRepository.getAll(id).stream()
                .map(CommentTo::new)
                .sorted(Comparator.comparing(CommentTo::getDateTime))
                .peek(commentTo -> commentTo.setUserTo(UserUtil.asTo(userRepository.get(commentTo.getUserId()))))
                .collect(Collectors.toList());
    }
}
