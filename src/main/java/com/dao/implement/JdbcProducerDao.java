package com.dao.implement;

import com.dao.ProductDao;
import com.entity.Producer;
import com.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProducerDao implements ProductDao {

    private Connection connection;
    private PreparedStatement preparedStatement;


    public JdbcProducerDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Kiev",
                                                         "root", "sasha");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    }


    @Override
    public void createTable() {
        Statement statement;
        String myTable = "CREATE TABLE IF NOT EXISTS producers (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50));";

        try {
            statement = connection.createStatement();

            statement.execute(myTable);
            System.out.println("Table producers Created");
            statement.close();
        }
        catch (SQLException e ) {
            e.printStackTrace();

            System.out.println("An error has occurred on Table Creation");
        }
    }




    public void insertInto(Producer producer) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO producers (name) VALUES(?)");
            preparedStatement.setString(1,producer.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTable(String set, String condition) {
        String insertStatement="UPDATE producers SET "  + set +" WHERE " + condition +";";
        try {
            preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteFromTable(String condition) {
        PreparedStatement preparedStatement;
        String insertStatement="DELETE FROM producers WHERE "  + condition + ";";
        try {
            preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Producer> selectAll() {

        List<Producer> producers = new ArrayList<Producer>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *from producers");
            while (resultSet.next()){
                Producer producer = new Producer();

                producer.setId(resultSet.getInt("id"));
                producer.setName(resultSet.getString("name"));
                producers.add(producer);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producers;    }
}
