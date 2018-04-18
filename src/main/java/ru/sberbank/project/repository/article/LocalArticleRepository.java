package ru.sberbank.project.repository.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sberbank.project.model.Article;
import ru.sberbank.project.repository.article.ArticleRepository;
import ru.sberbank.project.repository.article.CrudArticleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("localArticleRepo")
public class LocalArticleRepository implements ArticleRepository {

    @Autowired
    private CrudArticleRepository crudArticleRepository;

    @Override
    public Article save(Article article, int userId) {
        if (!article.isNew() && get(article.getId()) == null) {
            return null;
        }
        article.setUserId(userId);
        return crudArticleRepository.save(article);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudArticleRepository.delete(id, userId) != 0;
    }

    @Override
    public Article get(int id) {
        return crudArticleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Article> getAll(int userId) {
        return crudArticleRepository.getAll(userId);
    }
}
