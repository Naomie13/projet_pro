package repository;

import model.*;
import java.util.List;

public interface RestaurantRepository {
    // Menu
    void saveMenuItem(MenuItem item);
    List<MenuItem> loadMenuItems();
    void deleteMenuItem(int id);

    // Tables
    void saveTable(TableRestaurant table);
    List<TableRestaurant> loadTables();
    void updateTableStatus(int tableNumber, boolean status);
    void deleteTable(int id);

    // Ingredients
    void saveIngredient(Ingredient ingredient);
    List<Ingredient> loadIngredients();
    void updateIngredient(Ingredient ingredient);
    void deleteIngredient(int id);

    // Employees
    void saveEmployee(Employee employee);
    List<Employee> loadEmployees();
    void deleteEmployee(int id);
}