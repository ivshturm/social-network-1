package ru.sberbank.project.controller.article;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.project.model.ArticleTo;
import ru.sberbank.project.util.View;
import ru.sberbank.project.model.Article;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/profile/home")
public class ArticleAjaxController extends AbstractArticleController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Article> getAll(@PathVariable("id") int id) {
        return super.getAll(id);
    }

    @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleTo> getNews() {
        return super.getNews();
    }

    @Override
    @GetMapping(value = "/{id}")
    public Article get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Validated(View.Web.class) Article article) {
        if (article.isNew()) {
            super.create(article);
        } else {
            super.update(article, article.getId());
        }
    }
}