package ru.sberbank.project.service;

import ru.sberbank.project.model.Article;
import ru.sberbank.project.model.Comment;
import ru.sberbank.project.model.CommentTo;
import ru.sberbank.project.model.User;
import ru.sberbank.project.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ArticleService {
    Article get(int id) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Article> getAll(int userId);

    Article update(Article meal, int userId) throws NotFoundException;

    Article create(Article meal, int userId);

    Comment saveComment(Comment comment, int userId);

    boolean deleteComment(int id, int userId);

    List<CommentTo> getAllCommentsForArticleById(int id);

    List<Article> getNews(List<User> usersArticle);
}