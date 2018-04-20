package ru.sberbank.project.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sberbank.project.CommentTestData;
import ru.sberbank.project.UserTestData;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.model.Comment;
import ru.sberbank.project.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.sberbank.project.ArticleTestData.*;
import static ru.sberbank.project.CommentTestData.COMMENT_1;
import static ru.sberbank.project.CommentTestData.COMMENT_2;
import static ru.sberbank.project.UserTestData.USER_1_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/testDbPopulate.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ArticleServiceImplTest {

    @Autowired
    private ArticleService service;

    @Test
    public void get() {
        Article article = service.get(ARTICLE_1_ID);
        assertMatch(article, ARTICLE_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1);
    }

    @Test
    public void delete() {
        service.delete(ARTICLE_1_ID, USER_1_ID);
        assertMatch(service.getAll(USER_1_ID), ARTICLE_2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundArticleDelete() throws Exception {
        service.delete(1, USER_1_ID);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUserDelete() throws Exception {
        service.delete(ARTICLE_1_ID, 1);
    }

    @Test
    public void getAll() {
        List<Article> articles = service.getAll(USER_1_ID);
        assertMatch(articles, ARTICLE_1, ARTICLE_2);
    }

    @Test
    public void getAllEmpty() {
        List<Article> articles = service.getAll(1);
        assertMatch(articles);
    }

    @Test
    public void update() {
        Article updated = new Article(ARTICLE_1);
        updated.setName("UpdatedName");
        updated.setText("UpdatedText");
        service.update(updated, USER_1_ID);
        assertMatch(service.get(ARTICLE_1_ID), updated);
    }

    @Test
    public void create() {
        Article newArticle = new Article(null, "new article", "something is written", USER_1_ID);
        Article created = service.create(newArticle, USER_1_ID);
        newArticle.setId(created.getId());
        assertMatch(service.getAll(USER_1_ID), newArticle, ARTICLE_1, ARTICLE_2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNotFoundUser() {
        Article newArticle = new Article(null, "new article", "something is written", USER_1_ID);
        service.create(newArticle, 1);
    }

    @Test
    public void saveComment() {
        Comment newComment = new Comment(USER_1_ID, ARTICLE_1_ID, "something is written");
        Comment created = service.saveComment(newComment, USER_1_ID);
        newComment.setId(created.getId());
        CommentTestData.assertMatch(service.getAllCommentsForArticleById(ARTICLE_1_ID),
                        COMMENT_2, COMMENT_1, newComment);
    }

    @Test
    public void deleteComment() {
        service.deleteComment(CommentTestData.COMMENT_1_ID, UserTestData.USER_2_ID);
        CommentTestData.assertMatch(service.getAllCommentsForArticleById(ARTICLE_1_ID), COMMENT_2);

    }

    @Test
    public void getAllCommentsForArticleByIdSuccess() {
        List<Comment> comments = service.getAllCommentsForArticleById(ARTICLE_1_ID)
                .stream().map(Comment::new).collect(Collectors.toList());
        CommentTestData.assertMatch(comments, COMMENT_2, COMMENT_1);
    }

    @Test
    public void getAllCommentsForArticleByIdEmpty() {
        List<Comment> comments = service.getAllCommentsForArticleById(ARTICLE_2_ID)
                .stream().map(Comment::new).collect(Collectors.toList());
        CommentTestData.assertMatch(comments);
    }
}