package DAO;

import entity.Author;

import java.util.List;

public interface AuthorDAO {

    List<Author> getAll();

    Author getById(int id);

    void insert(Author author);

    void update(Author author);

    void deleteById(int id);

    void deleteAll();

}
