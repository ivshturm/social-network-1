package ru.sberbank.project.repository.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.project.model.Comment;

import java.util.List;

@Repository
public class LocalCommentRepository implements CommentRepository {

    @Autowired
    private CrudCommentRepository crudCommentRepository;

    @Override
    public Comment save(Comment comment) {
        return crudCommentRepository.save(comment);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudCommentRepository.delete(id, userId) != 0;
    }

    @Override
    public List<Comment> getAll(int userId) {
        return crudCommentRepository.getAllByArticleId(userId);
    }
}
