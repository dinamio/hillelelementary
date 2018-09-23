package com.dao.implement;

import com.dao.ProductDao;
import com.entity.Producer;
import com.entity.Product;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private Connection connection;
    private PreparedStatement preparedStatement;


    public JdbcProductDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Kiev", "root", "sasha");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    }


    @Override
    public void createTable() {
        Statement statement;
        String myTable = "CREATE TABLE IF NOT EXISTS products (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), producer_id INT,"
                    + "FOREIGN KEY (producer_id) REFERENCES producers(id));";

        try {
            statement = connection.createStatement();

            statement.execute(myTable);
            System.out.println("Table products Created");
            statement.close();
        }
        catch (SQLException e ) {
            e.printStackTrace();

            System.out.println("An error has occurred on Table Creation");
        }
    }



    public void insertInto(Product product) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO products (name,producer_id) VALUES(?,?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getProducer_id());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTable(String set, String condition) {
        String insertStatement="UPDATE products SET "  + set +" WHERE " + condition +";";
        try {
            preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromTable(String condition) {
        String insertStatement="DELETE FROM products WHERE "  + condition + ";";
        try {
            preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<Product>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *from products");
            while (resultSet.next()){
                Product product = new Product();
                Producer producer = new Producer();

                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                producer.setId(resultSet.getInt(3));
                producer.getName();
                product.setProducer(producer);
                products.add(product);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
