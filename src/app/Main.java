package app;

import facade.RestaurantFacade;
import model.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("========== TEST DELETE ==========\n");

        RestaurantFacade restaurant = new RestaurantFacade();

        // Ajouter si vide
        if (restaurant.getAllTables().isEmpty()) {
            restaurant.addTable(1, 1, 4);
            restaurant.addTable(2, 2, 6);
            restaurant.addTable(3, 3, 2);
        }

        if (restaurant.getMenu().isEmpty()) {
            restaurant.addFood(1, "Pizza", 12.0, "Fromage italiano", false);
            restaurant.addFood(2, "Burger", 9.5, "Boeuf grillé", false);
            restaurant.addDrink(3, "Coca Cola", 2.5, "Boisson gazeuse", false);
        }

        if (restaurant.getAllEmployees().isEmpty()) {
            restaurant.addEmployee(new Waiter(1, "Alice"));
            restaurant.addEmployee(new Waiter(2, "Bob"));
        }

        if (restaurant.getAllIngredients().isEmpty()) {
            restaurant.addIngredient(new Ingredient(1, "Tomates", 10.0, 3.0, "kg"));
            restaurant.addIngredient(new Ingredient(2, "Farine", 20.0, 5.0, "kg"));
        }

        restaurant.saveAll();

        System.out.println("Avant delete:");
        System.out.println("Tables: " + restaurant.getAllTables().size());
        System.out.println("Menu: " + restaurant.getMenu().size());
        System.out.println("Employés: " + restaurant.getAllEmployees().size());
        System.out.println("Ingrédients: " + restaurant.getAllIngredients().size());

        // ===== DELETE =====
        System.out.println("\n--- Suppression ---");
        restaurant.removeTable(2);
        restaurant.removeMenuItem(1);
        restaurant.removeEmployee(2);
        restaurant.removeIngredient(2);
        restaurant.saveAll();

        System.out.println("\nAprès delete:");
        System.out.println("Tables: " + restaurant.getAllTables().size());
        System.out.println("Menu: " + restaurant.getMenu().size());
        System.out.println("Employés: " + restaurant.getAllEmployees().size());
        System.out.println("Ingrédients: " + restaurant.getAllIngredients().size());

        // ===== VÉRIFIER DB =====
        System.out.println("\n--- Nouvelle instance (DB) ---");
        RestaurantFacade restaurant2 = new RestaurantFacade();
        System.out.println("Tables: " + restaurant2.getAllTables().size());
        System.out.println("Menu: " + restaurant2.getMenu().size());
        System.out.println("Employés: " + restaurant2.getAllEmployees().size());
        System.out.println("Ingrédients: " + restaurant2.getAllIngredients().size());

        System.out.println("\n========== FIN DU TEST ==========");
    }
}