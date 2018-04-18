package ru.sberbank.project.repository.comment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sberbank.project.model.Comment;

import java.util.List;

public interface CommentFeignClient {

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Comment save(Comment comment);

    @RequestMapping(value = "/comment/deleted/{id}/{userId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Comment delete(@PathVariable(value = "id") int id,
                   @PathVariable(value = "userId") int userId);

    @RequestMapping(value = "/comments/user/{userId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<Comment> getAll(@PathVariable(value = "userId") int userId);
}
