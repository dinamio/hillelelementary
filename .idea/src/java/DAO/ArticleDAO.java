package DAO;

import entity.Article;

import java.util.List;

public interface ArticleDAO {

    List<Article> getAll();

    void insert(Article article);

    void update(Article article);

    void deleteById(int id);
}
