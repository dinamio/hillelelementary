package DAO.impl;

import DAO.AuthorDAO;
import entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAuthorDAO implements AuthorDAO {

    public static final String CREATE_TABLE_IN_DATABASE = "CREATE TABLE authors ("
            + "authors_id INT (11) NOT NULL PRIMARY KEY AUTO_INCREMENT"
            + "authors_name VARCHAR(50) NOT NULL, )";
    public static final String SELECT_BY_ID_QUERY = "SELECT * FROM authors WHERE id = ?";
    public static final String SELECT_ALL_QUERY = "SELECT * FROM authors";
    public static final String INSERT_AUTHOR_QUERY = "INSERT INTO authors (id, name) VALUES (?, ?)";
    public static final String UPDATE_AUTHOR_QUERY = "UPDATE authors SET name = ?, WHERE id = ?";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM authors WHERE id = ?";
    public static final String DELETE_ALL_QUERY = "DELETE FROM authors";
    public static final String COLUMN_ID = "authors_id";
    public static final String COLUMN_NAME = "authors_name";

    private static ConnectionFactory connectionFactory;

    public JDBCAuthorDAO(ConnectionFactory connectionFactory) throws SQLException {

        this.connectionFactory = connectionFactory;
        createTable();
    }

    private static void createTable() throws SQLException {

        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_IN_DATABASE);
            System.out.println("Table was successful!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Author> getAll() {
        List<Author> listAuthor = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
                while (resultSet.next()) {
                    listAuthor.add(new Author(resultSet.getInt(COLUMN_ID),
                            resultSet.getString(COLUMN_NAME)));
                }
            }
        } catch (Exception e) {
            throw new DAOException("Method getAll() has thrown an exception.", e);
        }
        return listAuthor;
    }

    @Override
    public Author getById(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return new Author(resultSet.getInt(COLUMN_ID),
                            resultSet.getString(COLUMN_NAME));
                }
            }
        } catch (Exception e) {
            throw new DAOException(String.format("Method getById(id: '%d') has thrown an exception.", id), e);
        }
        return null;
    }

    @Override
    public void insert(Author author) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR_QUERY)) {
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setString(2, author.getName());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                throw new DAOException("Table 'authors' was not updated", null);
            }
        } catch (Exception e) {
            throw new DAOException(String.format("Method insert(author:'%s') has thrown an exception", author), e);
        }
    }

    @Override
    public void update(Author author) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR_QUERY)) {
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setString(2, author.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(String.format("Method update(author:'%s') has thrown an exception", author), e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(String.format("Method deleteById(id:'%d') has thrown an exception", id), e);
        }
    }

    @Override
    public void deleteAll() {
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_ALL_QUERY);
        } catch (Exception e) {
            throw new DAOException("Method deleteAll() has thrown an exception", e);
        }
    }
}
