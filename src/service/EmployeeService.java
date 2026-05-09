package service;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private List<Employee> employees;

    // Constructeur
    public EmployeeService() {

        employees = new ArrayList<>();
    }

    // Ajouter employé
    public void addEmployee(Employee employee) {

        employees.add(employee);
    }

    // Supprimer employé
    public void removeEmployee(Employee employee) {

        employees.remove(employee);
    }

    // Rechercher employé par ID
    public Employee findEmployeeById(int id) {

        for (Employee employee : employees) {

            if (employee.getId() == id) {
                return employee;
            }
        }

        return null;
    }

    // Afficher tous les employés
    public List<Employee> getAllEmployees() {

        return employees;
    }
}