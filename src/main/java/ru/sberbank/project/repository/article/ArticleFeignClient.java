package ru.sberbank.project.repository.article;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sberbank.project.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/article/{userId}",
                    produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Article save(@RequestBody Article article, @PathVariable("userId") Integer userId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/article/{id}/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Article delete(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId);

    @RequestMapping(method = RequestMethod.GET, value = "/article/{id}/{userId}")
    Article get(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId);

    @RequestMapping(method = RequestMethod.GET, value = "/articles/{userId}")
    List<Article> getAll(@PathVariable("userId") Integer userId);
}
