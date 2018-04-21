package ru.sberbank.project.controller.article;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.sberbank.project.model.Article;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ArticleRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleRestController extends AbstractArticleController {
    static final String REST_URL = "/rest/profile/home";

    @Override
    @GetMapping("/{id}")
    public Article get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<Article> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated @RequestBody Article article, @PathVariable("id") int id) {
        super.update(article, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> createWithLocation(@Validated @RequestBody Article article) {
        Article created = super.create(article);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}