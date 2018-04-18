package ru.sberbank.project.repository.comment;

import ru.sberbank.project.model.Comment;

import java.util.List;

public interface CommentRepository {

    Comment save(Comment comment);

    boolean delete(int id, int userId);

    List<Comment> getAll(int articleId);
}
