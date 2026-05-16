package app;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import facade.RestaurantFacade;
import model.*;
import strategy.*;

public class RestaurantConsole {

    private Scanner scanner;
    private RestaurantFacade restaurant;

    public RestaurantConsole() {
        scanner = new Scanner(System.in);
        restaurant = new RestaurantFacade();
        loadData();
    }

    public void start() {
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1: manageMenu(); break;
                case 2: manageOrders(); break;
                case 3: manageTables(); break;
                case 4: manageEmployees(); break;
                case 5: manageReservations(); break;
                case 6: manageStock(); break;
                case 7: manageReports(); break;
                case 8: restaurant.saveAll(); break;
                case 0: System.out.println("Application closed."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void displayMainMenu() {
        System.out.println("\n========== RESTAURANT SYSTEM ==========");
        System.out.println("1. Manage Menu");
        System.out.println("2. Manage Orders");
        System.out.println("3. Manage Tables");
        System.out.println("4. Manage Employees");
        System.out.println("5. Manage Reservations");
        System.out.println("6. Manage Stock");
        System.out.println("7. Reports");
        System.out.println("8. Save");
        System.out.println("0. Exit");
        System.out.print("Choice : ");
    }

    // ================= MENU =================
    private void manageMenu() {
        int choice;
        do {
            System.out.println("\n--- MENU MANAGEMENT ---");
            System.out.println("1. Add Food");
            System.out.println("2. Add Drink");
            System.out.println("3. Show Menu");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addFoodItem(); break;
                case 2: addDrinkItem(); break;
                case 3: showMenu(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addFoodItem() {
        scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Spicy (true/false): ");
        boolean spicy = scanner.nextBoolean();
        restaurant.addFood(id, name, price, description, spicy);
        System.out.println("Food added successfully.");
    }

    private void addDrinkItem() {
        scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Alcoholic (true/false): ");
        boolean alcoholic = scanner.nextBoolean();
        restaurant.addDrink(id, name, price, description, alcoholic);
        System.out.println("Drink added successfully.");
    }

    private void showMenu() {
        for (MenuItem item : restaurant.getMenu()) {
            System.out.println(item);
        }
    }

    // ================= ORDERS =================
    private void manageOrders() {
        int choice;
        do {
            System.out.println("\n--- ORDER MANAGEMENT ---");
            System.out.println("1. Create Order");
            System.out.println("2. Show Orders");
            System.out.println("3. Add Item to Order");
            System.out.println("4. Prepare Order");
            System.out.println("5. Order Ready");
            System.out.println("6. Serve Order");
            System.out.println("7. Pay Order");
            System.out.println("8. Cancel Order");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: createOrder(); break;
                case 2: showOrders(); break;
                case 3: addItemToOrder(); break;
                case 4: updateOrderState("prepare"); break;
                case 5: updateOrderState("ready"); break;
                case 6: updateOrderState("serve"); break;
                case 7: payOrderWithStrategy(); break;
                case 8: updateOrderState("cancel"); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void createOrder() {
        System.out.print("Order ID: ");
        int id = scanner.nextInt();
        System.out.print("Table Number: ");
        int tableNumber = scanner.nextInt();
        Order order = restaurant.createOrder(id, tableNumber);
        if (order == null) {
            System.out.println("Table not found.");
        } else {
            System.out.println("Order created successfully.");
        }
    }

    private void showOrders() {
        for (Order order : restaurant.getAllOrders()) {
            System.out.println(order);
        }
    }

    private void addItemToOrder() {
        System.out.print("Order ID: ");
        int orderId = scanner.nextInt();
        System.out.print("Menu Item ID: ");
        int itemId = scanner.nextInt();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        restaurant.addItemToOrder(orderId, itemId, quantity);
        System.out.println("Item added to order.");
    }

    private void updateOrderState(String action) {
        System.out.print("Order ID: ");
        int orderId = scanner.nextInt();
        switch (action) {
            case "prepare": restaurant.prepareOrder(orderId); break;
            case "ready":   restaurant.readyOrder(orderId); break;
            case "serve":   restaurant.serveOrder(orderId); break;
            case "cancel":  restaurant.cancelOrder(orderId); break;
        }
    }
    private void payOrderWithStrategy() {
        System.out.print("Order ID: ");
        int orderId = scanner.nextInt();

        System.out.println("Méthode de paiement:");
        System.out.println("1. Espèces (-2%)");
        System.out.println("2. Carte (+1.5%)");
        System.out.println("3. En ligne (+2.5%)");
        System.out.print("Choix: ");
        int choice = scanner.nextInt();

        PaymentStrategy strategy;
        switch (choice) {
            case 1: strategy = new CashStrategy(); break;
            case 2: strategy = new CardStrategy(); break;
            case 3: strategy = new OnlineStrategy(); break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        double total = restaurant.payOrder(orderId, strategy);
        System.out.println("Paiement effectué : " + String.format("%.2f", total) + " €");
    }

    
    // ================= TABLES =================
    private void manageTables() {
        int choice;
        do {
            System.out.println("\n--- TABLE MANAGEMENT ---");
            System.out.println("1. Add Table");
            System.out.println("2. Show Tables");
            System.out.println("3. Occupy Table");
            System.out.println("4. Free Table");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addTable(); break;
                case 2: showTables(); break;
                case 3: occupyTable(); break;
                case 4: freeTable(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addTable() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Table Number: ");
        int number = scanner.nextInt();
        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        restaurant.addTable(id, number, capacity);
        System.out.println("Table added successfully.");
    }

    private void showTables() {
        for (TableRestaurant table : restaurant.getAllTables()) {
            System.out.println(table);
        }
    }

    private void occupyTable() {
        System.out.print("Table Number: ");
        int number = scanner.nextInt();
        restaurant.occupyTable(number);
        System.out.println("Table occupied.");
    }

    private void freeTable() {
        System.out.print("Table Number: ");
        int number = scanner.nextInt();
        restaurant.freeTable(number);
        System.out.println("Table freed.");
    }

    // ================= EMPLOYEES =================
    private void manageEmployees() {
        int choice;
        do {
            System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Show Employees");
            System.out.println("3. Assign Waiter to Table");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addEmployee(); break;
                case 2: showEmployees(); break;
                case 3: assignWaiter(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void assignWaiter() {
        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();
        System.out.print("Table Number: ");
        int tableNumber = scanner.nextInt();
        restaurant.assignWaiterToTable(employeeId, tableNumber);
    }

    private void addEmployee() {
        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Waiter waiter = new Waiter(id, name);
        restaurant.addEmployee(waiter);
        System.out.println("Employee added successfully.");
    }

    private void showEmployees() {
        for (Employee employee : restaurant.getAllEmployees()) {
            System.out.println(employee);
        }
    }

    // ================= RESERVATIONS =================
    private void manageReservations() {
        int choice;
        do {
            System.out.println("\n--- RESERVATION MANAGEMENT ---");
            System.out.println("1. Add Reservation");
            System.out.println("2. Show Reservations");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Modify Reservation");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addReservation(); break;
                case 2: showReservations(); break;
                case 3: cancelReservation(); break;
                case 4: modifyReservation(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void cancelReservation() {
        scanner.nextLine();
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        restaurant.cancelReservation(name);
    }

    private void modifyReservation() {
        scanner.nextLine();
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("New table number: ");
        int tableNumber = scanner.nextInt();
        restaurant.modifyReservation(name, tableNumber, LocalDateTime.now());
        System.out.println("Réservation modifiée.");
    }

    private void addReservation() {
        scanner.nextLine();
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Customer phone: ");
        String phone = scanner.nextLine();
        System.out.print("Table number: ");
        int tableNumber = scanner.nextInt();
        restaurant.addReservation(name, phone, tableNumber);
        System.out.println("Reservation added successfully.");
    }

    private void showReservations() {
        for (Reservation reservation : restaurant.getAllReservations()) {
            System.out.println(reservation);
        }
    }
    
    
    private void manageStock() {
        int choice;
        do {
            System.out.println("\n--- STOCK MANAGEMENT ---");
            System.out.println("1. Add Ingredient");
            System.out.println("2. Show All Ingredients");
            System.out.println("3. Consume Ingredient");
            System.out.println("4. Restock Ingredient");
            System.out.println("5. Show Low Stock");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addIngredient(); break;
                case 2: restaurant.getAllIngredients().forEach(System.out::println); break;
                case 3: consumeIngredient(); break;
                case 4: restockIngredient(); break;
                case 5: restaurant.getLowStockIngredients().forEach(System.out::println); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addIngredient() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String name = scanner.nextLine();
        System.out.print("Quantité initiale: ");
        double quantity = scanner.nextDouble();
        System.out.print("Quantité minimum: ");
        double min = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Unité (kg/L/pcs): ");
        String unit = scanner.nextLine();
        restaurant.addIngredient(new Ingredient(id, name, quantity, min, unit));
        System.out.println("Ingrédient ajouté.");
    }

    private void consumeIngredient() {
        System.out.print("Ingredient ID: ");
        int id = scanner.nextInt();
        System.out.print("Quantité à consommer: ");
        double amount = scanner.nextDouble();
        restaurant.consumeIngredient(id, amount);
    }

    private void restockIngredient() {
        System.out.print("Ingredient ID: ");
        int id = scanner.nextInt();
        System.out.print("Quantité à ajouter: ");
        double amount = scanner.nextDouble();
        restaurant.restockIngredient(id, amount);
    }
    
    private void manageReports() {
        int choice;
        do {
            System.out.println("\n--- REPORTS ---");
            System.out.println("1. Sales Report");
            System.out.println("2. Popular Items");
            System.out.println("3. Table Report");
            System.out.println("4. Stock Report");
            System.out.println("5. Full Report");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: restaurant.generateSalesReport(); break;
                case 2: restaurant.generatePopularItemsReport(); break;
                case 3: restaurant.generateTableReport(); break;
                case 4: restaurant.generateStockReport(); break;
                case 5: restaurant.generateFullReport(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
    
    private void loadData() {
        List<TableRestaurant> tables = restaurant.loadTables();
        if (!tables.isEmpty()) {
            tables.forEach(t -> restaurant.addTable(t.getId(), t.getTableNumber(), t.getCapacity()));
            System.out.println("[LOAD] " + tables.size() + " tables chargées.");
        }
        List<Ingredient> ingredients = restaurant.loadIngredients();
        if (!ingredients.isEmpty()) {
            ingredients.forEach(i -> restaurant.addIngredient(i));
            System.out.println("[LOAD] " + ingredients.size() + " ingrédients chargés.");
        }
    }
    
}