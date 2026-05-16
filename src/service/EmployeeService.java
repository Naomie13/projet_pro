package service;

import model.Employee;
import model.Waiter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private List<Employee> employees;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    
    public void removeEmployeeById(int id) {
        employees.removeIf(e -> e.getId() == id);
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public boolean assignWaiterToTable(int employeeId, int tableNumber) {
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employé introuvable.");
            return false;
        }
        if (!(employee instanceof Waiter)) {
            System.out.println("Cet employé n'est pas un serveur.");
            return false;
        }
        Waiter waiter = (Waiter) employee;
        waiter.setAssignedTableNumber(tableNumber);
        System.out.println(waiter.getName() + " assigné à la table #" + tableNumber);
        return true;
    }
}