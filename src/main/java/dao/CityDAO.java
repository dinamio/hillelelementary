package dao;

import entity.City;

import java.sql.SQLException;
import java.util.List;

public interface CityDAO {

    //CREATE
    void add(City city) throws SQLException;

    //READ
    List<City> getAll() throws SQLException;

    City getById(int cityId) throws SQLException;

    //UPDATE
    void update(City city) throws SQLException;

    //DELETE
    void remove(City city) throws SQLException;
}
