package dao;

import businessLogic.Util;
import entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends Util implements CountryDao {

    Connection connection = getConnection();
    boolean createTables = createTables();

    @Override
    public void add(Country country) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO countries_of_the_world(country_of_the_world_id, country_of_the_world_name, country_Head_of_State, country_square, country_amount_of_people) VALUES (?,?,?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,country.getCountryId());
            preparedStatement.setString(2,country.getCountryName());
            preparedStatement.setString(3,country.getCountryHead());
            preparedStatement.setDouble(4,country.getCountrySquare());
            preparedStatement.setInt(5,country.getPopulation());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<Country> getAll() throws SQLException {
        List<Country> countries = new ArrayList<>();

        String sql = "SELECT country_of_the_world_id, country_of_the_world_name, country_Head_of_State, country_square, country_amount_of_people FROM countries_of_the_world";

        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Country country = new Country();
                country.setCountryId(resultSet.getInt("country_of_the_world_id"));
                country.setCountryName(resultSet.getString("country_of_the_world_name"));
                country.setCountryHead(resultSet.getString("country_Head_of_State"));
                country.setCountrySquare(resultSet.getDouble("country_square"));
                country.setPopulation(resultSet.getInt("country_amount_of_people"));
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
        return countries;
    }

    @Override
    public Country getById(int countryId) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT country_of_the_world_id, country_of_the_world_name, country_Head_of_State, country_square, country_amount_of_people FROM countries_of_the_world WHERE country_of_the_world_id=?";

        Country country = new Country();
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,countryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            country.setCountryId(resultSet.getInt("country_of_the_world_id"));
            country.setCountryName(resultSet.getString("country_of_the_world_name"));
            country.setCountryHead(resultSet.getString("country_Head_of_State"));
            country.setCountrySquare(resultSet.getDouble("country_square"));
            country.setPopulation(resultSet.getInt("country_amount_of_people"));


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        return country;
    }

    @Override
    public void update(Country country) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE countries_of_the_world SET country_of_the_world_name=?, country_Head_of_State=?, country_square=?,country_amount_of_people=? WHERE country_of_the_world_id=?";
        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,country.getCountryName());
            preparedStatement.setString(2,country.getCountryHead());
            preparedStatement.setDouble(3,country.getCountrySquare());
            preparedStatement.setInt(4,country.getPopulation());
            preparedStatement.setInt(5,country.getCountryId());

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    @Override
    public void remove(Country country) throws SQLException {
        Statement statement = null;

        String sql = "DELETE FROM countries_of_the_world WHERE country_of_the_world_id=1";
        try{
            statement = connection.createStatement();

            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
    }

}
