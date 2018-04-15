package ru.sberbank.project.controller.article;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.security.AuthorizedUser;
import ru.sberbank.project.util.UserUtil;

@Controller
public class ArticleController extends AbstractArticleController {

    @GetMapping("/article/{id}")
    public String getArticleById(ModelMap model, @PathVariable int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        Article article = super.get(id);
        model.addAttribute("article", article);
        model.addAttribute("dateTime", article.getDateTime().toString().replace('T', ' '));
        model.addAttribute("comments", super.getAllCommentsForArticle(id));
        model.addAttribute("authUser", authorizedUser);
        return "article";
    }

    @RequestMapping("/comment/delete/{id}/{articleId}")
    public String deleteCommentById(@PathVariable int id, @PathVariable int articleId) {
        super.deleteComment(id);
        return "redirect:/article/{articleId}";
    }


}
