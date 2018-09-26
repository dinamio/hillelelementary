package dao;

import entity.Department;
import entity.Employee;

import java.sql.Connection;
import java.util.List;

public interface HRdepartment {

    void addDepartment(String name, Connection connection);
    void hireEmployee(Employee employee, Connection connection);
    void fireEmployee(Employee employee, Connection connection);
    void substitution(Employee employee, Connection connection);
    Employee getEmployeeID(long id, Connection connection);
    Employee getEmployeeName(String name, Connection connection);
    List<Employee> getWorkers(Connection connection);
    Department getDepByID(long id, Connection connection);
}
