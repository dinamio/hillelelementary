package com.lessons;

import java.sql.*;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test");
        //подключение в котором можно бесконечно создавать таблицы "jdbc:h2:mem:test" и не будет каждый
        //и не будет каждый ругаться
        System.out.println("successful connection!!!");
        createTable(connection);
        writeRecord(connection);
        printTable(connection);
        updateTable(connection);
        printTable(connection);
        deleteRecord(connection);
        printTable(connection);

    }

    public static void createTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table student(" +
                    "id integer primary key auto_increment, " +
                    "username varchar(50));");
        }
        System.out.println("Table created !");
    }

    public static void writeRecord(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            statement.execute("insert into student(username) values('Sergey')");
        }
        System.out.println("Record created !");

    }

    public static void printTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " : " + resultSet.getString("username"));
            }
            System.out.println("----------------");
        }
    }

    public static void updateTable(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection
                .prepareStatement("insert into student(username) values(?)")) {

            //statement.setInt(1, 3);
            statement.setString(1, "fedya");
            statement.executeUpdate();
        }
        System.out.println("Update successful");
    }
    public static void deleteRecord(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            statement.execute("delete from student where id=1");
        }
        System.out.println("Record deleted !");
    }

}
