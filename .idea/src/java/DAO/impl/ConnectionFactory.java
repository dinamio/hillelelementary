package DAO.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    static final String H2_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:user";
    static final String LOGIN = "root";
    static final String PASSWORD = "root";

    private static ConnectionFactory instance;

    public ConnectionFactory() {
        try {
            Class.forName(H2_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Not found class " + H2_DRIVER, e);
        }
    }

    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }
}
