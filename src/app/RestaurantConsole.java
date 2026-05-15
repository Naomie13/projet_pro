package app;

import java.util.Scanner;

import facade.RestaurantFacade;
import model.*;

public class RestaurantConsole {

    private Scanner scanner;
    private RestaurantFacade restaurant;

    public RestaurantConsole() {
        scanner = new Scanner(System.in);
        restaurant = new RestaurantFacade();
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
                case 7: updateOrderState("pay"); break;
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
            case "pay":     restaurant.payOrder(orderId); break;
            case "cancel":  restaurant.cancelOrder(orderId); break;
        }
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
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addEmployee(); break;
                case 2: showEmployees(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
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
            System.out.println(employee.getId() + " - " + employee.getName());
        }
    }

    // ================= RESERVATIONS =================
    private void manageReservations() {
        int choice;
        do {
            System.out.println("\n--- RESERVATION MANAGEMENT ---");
            System.out.println("1. Add Reservation");
            System.out.println("2. Show Reservations");
            System.out.println("0. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: addReservation(); break;
                case 2: showReservations(); break;
                case 0: break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
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
}