package businessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:test";
    Connection connection = null;

    public Connection getConnection (){
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection is OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection is FAULTY");
        }

        return connection;
    }

    public boolean createTables(){
        try {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE countries_of_the_world ("+
                "country_of_the_world_id int(11) NOT NULL, " +
                "country_of_the_world_name varchar(65) NOT NULL, " +
                "country_Head_of_State varchar(70) NOT NULL, " +
                "country_square double NOT NULL, " +
                "country_amount_of_people int(11) NOT NULL, " +
                "PRIMARY KEY (country_of_the_world_id))";
        statement.executeUpdate(sql);
        System.out.println("Table is successfully created");

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Table is not successfully created");
    }
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE CITIES "+
                    "(city_id int(11) NOT NULL, " +
                    "city_name varchar(70) NOT NULL, " +
                    "city_head varchar(70) NOT NULL, " +
                    "country_of_the_world_id int(11) NOT NULL, " +
                    "PRIMARY KEY (city_id), " +
                    "FOREIGN KEY (country_of_the_world_id) REFERENCES countries_of_the_world (country_of_the_world_id))";
            statement.executeUpdate(sql);
            System.out.println("Table is successfully created");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table is not successfully created");
        }
        return true;
    }

    public void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
