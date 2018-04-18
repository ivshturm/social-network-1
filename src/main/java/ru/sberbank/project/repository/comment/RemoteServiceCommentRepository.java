package ru.sberbank.project.repository.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.project.model.Comment;

import java.util.List;

@Repository("remoteCommentRepo")
public class RemoteServiceCommentRepository implements CommentRepository {

    @Autowired
    private CommentFeignClient commentFeignClient;


    @Override
    public Comment save(Comment comment) {
        return commentFeignClient.save(comment);
    }

    @Override
    public boolean delete(int id, int userId) {
        return commentFeignClient.delete(id, userId) != null;
    }

    @Override
    public List<Comment> getAll(int userId) {
        return commentFeignClient.getAll(userId);
    }
}
