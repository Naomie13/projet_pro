package repository;

import config.DataBase;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository implements RestaurantRepository {

    private Connection conn;

    public DatabaseRepository() {
        this.conn = DataBase.getInstance().getConnection();
    }

    // ===== MENU =====
    @Override
    public void saveMenuItem(MenuItem item) {
        String sql = "INSERT INTO menu_items (id, name, price, description, type, extra) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?, price=?, description=?, extra=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String type = (item instanceof FoodItem) ? "food" : "drink";
            boolean extra = (item instanceof FoodItem) ? ((FoodItem) item).isSpicy() : ((DrinkItem) item).isAlcoholic();
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getPrice());
            stmt.setString(4, item.getDescription());
            stmt.setString(5, type);
            stmt.setBoolean(6, extra);
            stmt.setString(7, item.getName());
            stmt.setDouble(8, item.getPrice());
            stmt.setString(9, item.getDescription());
            stmt.setBoolean(10, extra);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur saveMenuItem: " + e.getMessage());
        }
    }

    @Override
    public List<MenuItem> loadMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String type = rs.getString("type");
                boolean extra = rs.getBoolean("extra");
                if (type.equals("food")) {
                    items.add(new FoodItem(id, name, price, description, extra));
                } else {
                    items.add(new DrinkItem(id, name, price, description, extra));
                }
            }
        } catch (SQLException e) {
            System.out.println("[DB] Erreur loadMenuItems: " + e.getMessage());
        }
        return items;
    }

    @Override
    public void deleteMenuItem(int id) {
        String sql = "DELETE FROM menu_items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur deleteMenuItem: " + e.getMessage());
        }
    }

    // ===== TABLES =====
    @Override
    public void saveTable(TableRestaurant table) {
        String sql = "INSERT INTO tables_restaurant (id, table_number, capacity, status) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE capacity=?, status=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, table.getId());
            stmt.setInt(2, table.getTableNumber());
            stmt.setInt(3, table.getCapacity());
            stmt.setBoolean(4, table.isStatus());
            stmt.setInt(5, table.getCapacity());
            stmt.setBoolean(6, table.isStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur saveTable: " + e.getMessage());
        }
    }

    @Override
    public List<TableRestaurant> loadTables() {
        List<TableRestaurant> tables = new ArrayList<>();
        String sql = "SELECT * FROM tables_restaurant";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int number = rs.getInt("table_number");
                int capacity = rs.getInt("capacity");
                boolean status = rs.getBoolean("status");
                tables.add(new TableRestaurant(id, number, capacity, status));
            }
        } catch (SQLException e) {
            System.out.println("[DB] Erreur loadTables: " + e.getMessage());
        }
        return tables;
    }

    @Override
    public void updateTableStatus(int tableNumber, boolean status) {
        String sql = "UPDATE tables_restaurant SET status = ? WHERE table_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, tableNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur updateTableStatus: " + e.getMessage());
        }
    }

    @Override
    public void deleteTable(int id) {
        String sql = "DELETE FROM tables_restaurant WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur deleteTable: " + e.getMessage());
        }
    }

    // ===== INGREDIENTS =====
    @Override
    public void saveIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO ingredients (id, name, quantity, min_quantity, unit) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE quantity=?, min_quantity=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ingredient.getId());
            stmt.setString(2, ingredient.getName());
            stmt.setDouble(3, ingredient.getQuantity());
            stmt.setDouble(4, ingredient.getMinQuantity());
            stmt.setString(5, ingredient.getUnit());
            stmt.setDouble(6, ingredient.getQuantity());
            stmt.setDouble(7, ingredient.getMinQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur saveIngredient: " + e.getMessage());
        }
    }

    @Override
    public List<Ingredient> loadIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM ingredients";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ingredients.add(new Ingredient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("quantity"),
                    rs.getDouble("min_quantity"),
                    rs.getString("unit")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[DB] Erreur loadIngredients: " + e.getMessage());
        }
        return ingredients;
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        String sql = "UPDATE ingredients SET quantity = ?, min_quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, ingredient.getQuantity());
            stmt.setDouble(2, ingredient.getMinQuantity());
            stmt.setInt(3, ingredient.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur updateIngredient: " + e.getMessage());
        }
    }

    @Override
    public void deleteIngredient(int id) {
        String sql = "DELETE FROM ingredients WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur deleteIngredient: " + e.getMessage());
        }
    }

    // ===== EMPLOYEES =====
    @Override
    public void saveEmployee(Employee employee) {
        String role = (employee instanceof Waiter) ? "waiter" : "chief";
        int assignedTable = (employee instanceof Waiter) ? ((Waiter) employee).getAssignedTableNumber() : -1;
        String sql = "INSERT INTO employees (id, name, role, assigned_table) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?, assigned_table=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, role);
            stmt.setInt(4, assignedTable);
            stmt.setString(5, employee.getName());
            stmt.setInt(6, assignedTable);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur saveEmployee: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                int assignedTable = rs.getInt("assigned_table");
                if (role.equals("waiter")) {
                    Waiter waiter = new Waiter(id, name);
                    waiter.setAssignedTableNumber(assignedTable);
                    employees.add(waiter);
                } else {
                    employees.add(new Chief(id, name));
                }
            }
        } catch (SQLException e) {
            System.out.println("[DB] Erreur loadEmployees: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[DB] Erreur deleteEmployee: " + e.getMessage());
        }
    }
}