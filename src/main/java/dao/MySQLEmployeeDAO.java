package dao;

import entity.Department;
import entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmployeeDAO implements HRdepartment {
    @Override
    public void addDepartment(String name, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into department (name) values(?)");
            ps.setString(1,name);
            ps.execute();
            System.out.println(name+" has added");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hireEmployee(Employee employee, Connection connection) {
        employee.setDepartment(getDepByID(employee.getDepartment().getId(), connection));
        if (employee.getDepartment() != null){
            try {
                PreparedStatement ps = connection.prepareStatement("insert into employee (name, surname, department) values (?,?,?)");
                        ps.setString(1,employee.getName());
                ps.setString(2,employee.getSurname());
                ps.setInt(3, (int) employee.getDepartment().getId());
                ps.execute();
                System.out.println("employee added!!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fireEmployee(Employee employee, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE from employee where id=?;");
            ps.setInt(1, (int) employee.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Cant delete " + employee + " couse " + e.toString());
        }
    }

    @Override
    public void substitution(Employee employee, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("update employee e set e.name = ?, e.surname = ?, e.department = ? where e.id =?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setInt(3, (int) employee.getDepartment().getId());
            ps.setInt(4, (int)employee.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Update did not occur : " + e.toString());
        }
    }

    @Override
    public Employee getEmployeeID(long id, Connection connection) {
        Employee employee = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from employee e where e.id=?");
            ps.setInt(1, (int) id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = registrationInDepartment(rs, connection);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong with employee");
        }
        return employee;
    }

    @Override
    public Employee getEmployeeName(String name, Connection connection) {
        Employee employee = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * from employee e where e.name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = registrationInDepartment(rs, connection);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong with employee");
        }
        return employee;
    }

    @Override
    public List<Employee> getWorkers(Connection connection) {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT * from employee");
            while (rs.next()) {
                employees.add(registrationInDepartment(rs, connection));
            }
        } catch (SQLException e) {
            System.out.println("=(");
        }
        return employees;
    }

    private Employee registrationInDepartment(ResultSet rs, Connection connection) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setSurname(rs.getString("surname"));
        employee.setDepartment(getDepByID(rs.getInt("department"), connection));
        return employee;
    }

    @Override
    public Department getDepByID(long id, Connection connection) {
        Department department = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from department d where d.id = ?");
            ps.setInt(1, (int) id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
