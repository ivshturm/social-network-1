package ru.sberbank.project.feign;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.project.model.Article;

import java.util.List;

public interface NewsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/news",
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<Article> getAll(@RequestBody List<Integer> usersId);
}
