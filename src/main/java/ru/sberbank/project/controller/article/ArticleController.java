package ru.sberbank.project.controller.article;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sberbank.project.model.Article;

@Controller
public class ArticleController extends AbstractArticleController {

    @GetMapping("/article/{id}")
    public String getArticleById(ModelMap model, @PathVariable int id) {
        Article article = super.get(id);
        model.addAttribute("article", article);
        model.addAttribute("dateTime", article.getDateTime().toString().replace('T', ' '));
        model.addAttribute("comments", super.getAllCommentsForArticle(id));
        return "article";
    }


}
