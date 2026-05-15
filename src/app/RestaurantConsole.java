package app;

import java.util.Scanner;
import java.time.LocalDateTime;
import service.ReservationService;
import service.MenuService;

import model.*;
import service.*;

public class RestaurantConsole {

    private Scanner scanner;
    
    private MenuService menuService;
    private OrderService orderService;
    private TableRestaurantService tableService;
    private EmployeeService employeeService;
    private ReservationService reservationService;

    public RestaurantConsole() {

        scanner = new Scanner(System.in);

        orderService = new OrderService();
        tableService = new TableRestaurantService();
        employeeService = new EmployeeService();
        reservationService = new ReservationService();
        menuService = new MenuService();
    }

    public void start() {

        int choice;

        do {

            displayMainMenu();

            choice = scanner.nextInt();

            switch (choice) {

            case 1:
                manageMenu();
                break;

            case 2:
                manageOrders();
                break;

            case 3:
                manageTables();
                break;

            case 4:
                manageEmployees();
                break;

            case 5:
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
        System.out.println("1. Manage Menu");
        System.out.println("2. Manage Orders");
        System.out.println("3. Manage Tables");
        System.out.println("4. Manage Employees");
        System.out.println("5. Manage Reservations");
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
                   
                case 3:
                    addItemToOrder();
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

        System.out.print("Table Number : ");
        int tableNumber = scanner.nextInt();

        TableRestaurant table =
                tableService.findTableByNumber(tableNumber);

        if (table == null) {
            System.out.println("Table not found.");
            return;
        }

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

        int choice;

        do {
            System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Show Employees");
            System.out.println("0. Back");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    showEmployees();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void addEmployee() {

        System.out.print("Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        //System.out.print("Salary: ");
       // double salary = scanner.nextDouble();

        Waiter waiter = new Waiter(id, name);

        employeeService.addEmployee(waiter);

        System.out.println("Employee added successfully.");
    }
    
    private void showEmployees() {

        for (Employee employee : employeeService.getAllEmployees()) {
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

                case 1:
                    addReservation();
                    break;

                case 2:
                    showReservations();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
    
    private void addReservation() {

        scanner.nextLine();

        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        System.out.print("Customer phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(name, phone);

        System.out.print("Table number: ");
        int tableNumber = scanner.nextInt();

        TableRestaurant table =
                tableService.findTableByNumber(tableNumber);

        if (table == null) {
            System.out.println("Table not found.");
            return;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        table,
                        LocalDateTime.now()
                );

        reservationService.addReservation(reservation);

        System.out.println("Reservation added successfully.");
    }
    
    private void showReservations() {

        for (Reservation reservation :
                reservationService.getReservations()) {

            System.out.println(reservation);
        }
    }
    
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

                case 1:
                    addFoodItem();
                    break;

                case 2:
                    addDrinkItem();
                    break;

                case 3:
                    showMenu();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice.");
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

        FoodItem food = new FoodItem(id, name, price, description, spicy);

        menuService.addItem(food);

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

        DrinkItem drink = new DrinkItem(id, name, price, description, alcoholic);

        menuService.addItem(drink);

        System.out.println("Drink added successfully.");
    }
    
    private void showMenu() {

        for (MenuItem item : menuService.getAllItems()) {
            System.out.println(item);
        }
    }
    
    private void addItemToOrder() {

        System.out.print("Order ID: ");
        int orderId = scanner.nextInt();

        Order order = orderService.findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.print("Menu Item ID: ");
        int itemId = scanner.nextInt();

        MenuItem item = menuService.findItemById(itemId);

        if (item == null) {
            System.out.println("Menu item not found.");
            return;
        }

        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();

        OrderItem orderItem = new OrderItem(item, quantity);

        orderService.addItemToOrder(order, orderItem);

        System.out.println("Item added to order successfully.");
    }
}