package dao;

import entity.Car;
import entity.CarColor;
import entity.CarType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCarDAO implements ICarDAO {

    private Connection connection;
    private final String BASEURL = "jdbc:mysql://localhost:3306/cars";


    public MysqlCarDAO(String user, String password) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(BASEURL, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", user, password);
                connection.createStatement().execute("CREATE DATABASE cars;");
                connection.createStatement().execute("use cars;");
                connection.createStatement().execute("CREATE TABLE carType(id int PRIMARY KEY AUTO_INCREMENT,name varchar(20));");
                connection.createStatement().execute("CREATE TABLE car ( id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), type int, color int);");
            } catch (SQLException sqle) {
                System.out.println("Real trouble with connection : \n" + e.toString());
            }
        }

    }

    public void addType(String name) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO carType (name) VALUES (?)");
            ps.setString(1, name);
            ps.execute();
            System.out.println("Type " + name + " success added");
        } catch (SQLException e) {
            System.out.println("Type " + name + " cant added, sorry");
        }

    }

    /**
     * Check car type in the car object and create new write in base for car
     */
    public void addCar(Car car) {
        car.setType(getTypeById(car.getType().getId()));
        if (car.getType() != null) { // validation for one to many
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO car (name, type, color) VALUES (?,?,?)");
                ps.setString(1, car.getName());
                ps.setInt(2, (int) car.getType().getId());
                ps.setInt(3, car.getColor().ordinal());
                ps.execute();
                System.out.println(car.getName() + " added to car as " + car.getType().getName());
            } catch (SQLException e) {
                System.out.println("Something wrong. Maybe people in car factory in vacation");
            }
        } else {
            System.out.println("Car type d't found in base. Incorrect id.");
        }
    }

    /**
     * return type if it present or null else
     */
    public CarType getTypeById(long id) {
        CarType type = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from carType t where t.id = ?");
            ps.setInt(1, (int) id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                type = new CarType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("some trouble with find type id by name");
        }

        return type;
    }

    /**
     * return car if it present or null
     */
    public Car getCarById(long id) {
        Car car = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from car c where c.id=?");
            ps.setInt(1, (int) id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                car = convertRSCar(rs);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong with car finds");
        }
        return car;
    }

    public Car getCarByName(String name) {
        Car car = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from car c where c.name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car = convertRSCar(rs);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong with car finds by name");
        }

        return car;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<Car>();

        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * from car");
            while (rs.next()) {
                cars.add(convertRSCar(rs));
            }
        } catch (SQLException e) {
            System.out.println("=(");
        }

        return cars;
    }

    public void changeCar(Car car) {
        try {
            PreparedStatement ps = connection.prepareStatement("update car c set c.name = ?,c.type = ?, c.color = ? where c.id =?");
            ps.setString(1, car.getName());
            ps.setInt(2, (int) car.getType().getId());
            ps.setInt(3, car.getColor().ordinal());
            ps.setInt(4, (int) car.getId());

            ps.execute();

        } catch (SQLException e) {
            System.out.println("Update impossible couse : " + e.toString());
        }
    }

    /**
     * Delete car by id or all any fields
     */
    public void deleteCar(Car car) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE from car where id=?;");

            ps.setInt(1, (int) car.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Cant delete " + car + " couse " + e.toString());
        }
    }

    private Car convertRSCar(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setId(rs.getInt("id"));
        car.setName(rs.getString("name"));
        car.setType(getTypeById(rs.getInt("type")));
        car.setColor(CarColor.values()[rs.getInt("color")]);

        return car;
    }
}
