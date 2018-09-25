package DAO.impl;

import DAO.ArticleDAO;
import entity.Article;
import entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCArticleDAO implements ArticleDAO {

    private static ConnectionFactory connectionFactory;

    public JDBCArticleDAO(ConnectionFactory connectionFactory) throws SQLException {

        this.connectionFactory = connectionFactory;
        createTable();
    }

    private static void createTable() throws SQLException {

        String createTable = "CREATE TABLE article(article_id int PRIMARY KEY AUTO_INCREMENT, "
                + "article_name varchar(255) NOT NULL, author int, "
                + "FOREIGN KEY (author) REFERENCES author(id))";

        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTable);
            System.out.println("Table was successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Article> getAll() {

        String SELECT_ALL_QUERY = "SELECT * FROM authors";
        String COLUMN_ID = "article_id";
        String COLUMN_NAME = "article_name";

        List<Article> listArticle = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
                while (resultSet.next()) {
                    Article article = new Article();
                    article.setId(resultSet.getInt(COLUMN_ID));
                    article.setName(resultSet.getString(COLUMN_NAME));
                    article.setAuthor(new Author(resultSet.getInt("article_id"),resultSet.getString("article")));
                    listArticle.add(article);
                }
            }
        } catch (Exception e) {
            throw new DAOException("Method getAll() has thrown an exception.", e);
        }
        return listArticle;
    }

    @Override
    public void insert(Article article) {
        String INSERT_ARTICLE_QUERY = "INSERT INTO authors (id, name, author) VALUES (?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICLE_QUERY)) {
            preparedStatement.setString(1, article.getName());
            preparedStatement.setInt(2, article.getId());
            preparedStatement.setObject(3, article.getAuthor());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new DAOException("Table 'article' was not updated", null);
            }
        } catch (Exception e) {
            throw new DAOException(String.format("Method insert(author:'%s') has thrown an exception", article), e);
        }
    }

    @Override
    public void update(Article article) {
        String UPDATE_AUTHOR_QUERY = "UPDATE paintings SET name = ?, artist = ? WHERE id = ?;";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR_QUERY)) {
            preparedStatement.setString(1, article.getName());
            preparedStatement.setInt( 2, article.getId());
            preparedStatement.setObject(3, article.getAuthor());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(String.format("Method update(author:'%s') has thrown an exception", article), e);
        }
    }

    @Override
    public void deleteById(int id) {
        String DELETE_BY_ID_QUERY = "DELETE FROM article WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(String.format("Method deleteById(id:'%d') has thrown an exception", id), e);
        }
    }
}
