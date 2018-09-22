package com.paintings.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public Connection createConnection(String host, String db, String user, String password){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("There are some problems with driver loading");
        }

        try {
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "false");
            connection = DriverManager.getConnection("jdbc:mysql://" + host +"/" +db + "?serverTimezone=Europe/Kiev", properties);
        } catch (SQLException e) {
            System.out.println("failed connection");
        }
        return connection;
    }

}
