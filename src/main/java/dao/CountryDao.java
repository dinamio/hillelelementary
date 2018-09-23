package dao;

import entity.Country;

import java.sql.SQLException;
import java.util.List;

public interface CountryDao {

    //CREATE
    void add(Country country) throws SQLException;

    //READ
    List<Country> getAll() throws SQLException;

    Country getById(int countryId) throws SQLException;

    //UPDATE
    void update(Country country) throws SQLException;

    //DELETE
    void remove(Country country) throws SQLException;
}
