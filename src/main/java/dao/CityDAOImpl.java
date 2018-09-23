package dao;

import businessLogic.Util;
import entity.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl extends Util implements CityDAO {

    Connection connection = getConnection();

    @Override
    public void add(City city) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO CITIES(CITY_ID, CITY_NAME, CITY_HEAD, COUNTRY_OF_THE_WORLD_ID) VALUES (?,?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,city.getCityId());
            preparedStatement.setString(2,city.getCityName());
            preparedStatement.setString(3,city.getCityHead());
            preparedStatement.setInt(4,city.getCountryId());

            preparedStatement.executeUpdate();
            System.out.println(city.getCityName() + "was added");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<City> getAll() throws SQLException {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT CITY_ID, CITY_NAME, CITY_HEAD, COUNTRY_OF_THE_WORLD_ID FROM CITIES";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                City city = new City();
                city.setCityId(resultSet.getInt("CITY_ID"));
                city.setCityName(resultSet.getString("CITY_NAME"));
                city.setCityHead(resultSet.getString("CITY_HEAD"));
                city.setCountryId(resultSet.getInt("COUNTRY_OF_THE_WORLD_ID"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
        return cities;
    }

    @Override
    public City getById(int cityId) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT CITY_ID, CITY_NAME, CITY_HEAD, COUNTRY_OF_THE_WORLD_ID FROM CITIES WHERE CITY_ID=?";

        City city = new City();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cityId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            city.setCityId(resultSet.getInt("CITY_ID"));
            city.setCityName(resultSet.getString("CITY_NAME"));
            city.setCityHead(resultSet.getString("CITY_HEAD"));
            city.setCountryId(resultSet.getInt("COUNTRY_OF_THE_WORLD_ID"));


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        return city;
    }

    @Override
    public void update(City city) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE CITIES SET CITY_NAME=?, CITY_HEAD=?, COUNTRY_OF_THE_WORLD_ID=? WHERE CITY_ID=?";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,city.getCityName());
            preparedStatement.setString(2,city.getCityHead());
            preparedStatement.setInt(3,city.getCountryId());
            preparedStatement.setInt(4,city.getCityId());

            preparedStatement.executeUpdate();
            System.out.println(city.getCityName() + "was updated");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    @Override
    public void remove(City city) throws SQLException {
        Statement statement = null;
        Integer id = city.getCityId();

        String sql = "DELETE FROM CITIES WHERE CITY_ID=" + id.toString();
        try{
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println(city.getCityName() + "was deleted");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
    }
}

