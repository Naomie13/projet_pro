package app;

import java.util.Scanner;

import model.*;
import service.*;

public class RestaurantConsole {

    private Scanner scanner;

    private OrderService orderService;
    private TableRestaurantService tableService;
    private EmployeeService employeeService;

    public RestaurantConsole() {

        scanner = new Scanner(System.in);

        orderService = new OrderService();
        tableService = new TableRestaurantService();
        employeeService = new EmployeeService();
    }

    public void start() {

        int choice;

        do {

            displayMainMenu();

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    manageOrders();
                    break;

                case 2:
                    manageTables();
                    break;

                case 3:
                    manageEmployees();
                    break;

                case 4:
                    manageReservations();
                    break;

                case 0:
                    System.out.println("Application closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    // ================= MAIN MENU =================
    private void displayMainMenu() {

        System.out.println("\n========== RESTAURANT SYSTEM ==========");
        System.out.println("1. Manage Orders");
        System.out.println("2. Manage Tables");
        System.out.println("3. Manage Employees");
        System.out.println("4. Manage Reservations");
        System.out.println("0. Exit");
        System.out.print("Choice : ");
    }

    // ================= ORDERS =================
    private void manageOrders() {

        int choice;

        do {

            System.out.println("\n--- ORDER MANAGEMENT ---");
            System.out.println("1. Create Order");
            System.out.println("2. Show Orders");
            System.out.println("3. Add Item to Order");
            System.out.println("0. Back");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createOrder();
                    break;

                case 2:
                    showOrders();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void createOrder() {

        System.out.print("Order ID : ");
        int id = scanner.nextInt();

        System.out.print("Table ID : ");
        int tableId = scanner.nextInt();

        TableRestaurant table =
                tableService.findTableByNumber(tableId);

        Order order = orderService.createOrder(id, table);

        System.out.println("Order created successfully.");
    }

    private void showOrders() {

        for (Order order : orderService.getAllOrders()) {
            System.out.println(order);
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

                case 1:
                    addTable();
                    break;

                case 2:
                    showTables();
                    break;

                case 3:
                    occupyTable();
                    break;

                case 4:
                    freeTable();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void addTable() {

        System.out.print("ID : ");
        int id = scanner.nextInt();

        System.out.print("Table Number : ");
        int number = scanner.nextInt();

        System.out.print("Capacity : ");
        int capacity = scanner.nextInt();

        TableRestaurant table =
                new TableRestaurant(id, number, capacity, true);

        tableService.addTable(table);

        System.out.println("Table added successfully.");
    }

    private void showTables() {

        for (TableRestaurant table : tableService.getAllTables()) {
            System.out.println(table);
        }
    }

    private void occupyTable() {

        System.out.print("Table Number : ");
        int number = scanner.nextInt();

        TableRestaurant table =
                tableService.findTableByNumber(number);

        tableService.occupyTable(table);

        System.out.println("Table occupied.");
    }

    private void freeTable() {

        System.out.print("Table Number : ");
        int number = scanner.nextInt();

        TableRestaurant table =
                tableService.findTableByNumber(number);

        tableService.freeTable(table);

        System.out.println("Table freed.");
    }

    // ================= EMPLOYEES =================
    private void manageEmployees() {

        System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
        System.out.println("Coming soon...");
    }

    // ================= RESERVATIONS =================
    private void manageReservations() {

        System.out.println("\n--- RESERVATION MANAGEMENT ---");
        System.out.println("Coming soon...");
    }
}