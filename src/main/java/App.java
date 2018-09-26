import dao.MySQLEmployeeDAO;
import entity.Department;
import entity.Employee;

import java.sql.*;
import java.util.List;

public class App {
    private static Connection connection = null;


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            log("-------- JDBC connection to MySQL DB locally ------------");
            makeJDBCConnection();

            log("\n---------- Connection from DB succesful ----------");
 //  done         createTableofBase(connection);
            MySQLEmployeeDAO employeeDAO = new MySQLEmployeeDAO();
//   done         employeeDAO.addDepartment("development", connection);
            Employee employee = new Employee();
            employee.setName("Pavlik");
            employee.setSurname("Morozov");
            employee.setDepartment(new Department().setId(2));
            employeeDAO.hireEmployee(employee,connection);

            List<Employee> employeeList = employeeDAO.getWorkers(connection);
            for (Employee worker : employeeList) {
                System.out.println(worker);
            }
            employee.setId(3);
            employeeDAO.fireEmployee(employee, connection);
            System.out.println("------------------------------------------------------------------");
            List<Employee> newEmployeeList = employeeDAO.getWorkers(connection);
            for (Employee worker : newEmployeeList) {
                System.out.println(worker);
            }

            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            log("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?serverTimezone=Europe/Kiev", "root", "root");
            if (connection != null) {
                log("Connection Successful!");
            } else {
                log("Failed to make connection!");
            }
        } catch (SQLException e) {
            log("MySQL Connection Failed!");
            e.printStackTrace();
        }

    }

    public static void createTableofBase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            statement.execute("create table employee(" +
                    "id int primary key auto_increment, " +
                    "name varchar(20), surname varchar(20), department int);");
            statement.execute("create table department(id int primary key auto_increment, name varchar(20))");

        }
        System.out.println("Table created !");
    }

    private static void log(String string) {
        System.out.println(string);

    }
}
