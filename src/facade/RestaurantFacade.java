package facade;

import factory.MenuItemFactory;
import model.*;
import service.*;

import java.time.LocalDateTime;
import java.util.List;

public class RestaurantFacade {

    private MenuService menuService;
    private OrderService orderService;
    private TableRestaurantService tableService;
    private EmployeeService employeeService;
    private ReservationService reservationService;

    public RestaurantFacade() {
        this.menuService = new MenuService();
        this.orderService = new OrderService();
        this.tableService = new TableRestaurantService();
        this.employeeService = new EmployeeService();
        this.reservationService = new ReservationService();
    }

    // ===== MENU =====
    public void addFood(int id, String name, double price, String description, boolean spicy) {
        MenuItem item = MenuItemFactory.create("food", id, name, price, description, spicy);
        menuService.addItem(item);
    }

    public void addDrink(int id, String name, double price, String description, boolean alcoholic) {
        MenuItem item = MenuItemFactory.create("drink", id, name, price, description, alcoholic);
        menuService.addItem(item);
    }

    public List<MenuItem> getMenu() {
        return menuService.getAllItems();
    }

    public MenuItem findMenuItem(int id) {
        return menuService.findItemById(id);
    }

    // ===== TABLES =====
    public void addTable(int id, int number, int capacity) {
        TableRestaurant table = new TableRestaurant(id, number, capacity, true);
        tableService.addTable(table);
    }

    public List<TableRestaurant> getAllTables() {
        return tableService.getAllTables();
    }

    public TableRestaurant findTable(int number) {
        return tableService.findTableByNumber(number);
    }

    public void occupyTable(int number) {
        TableRestaurant table = tableService.findTableByNumber(number);
        if (table != null) tableService.occupyTable(table);
    }

    public void freeTable(int number) {
        TableRestaurant table = tableService.findTableByNumber(number);
        if (table != null) tableService.freeTable(table);
    }

    // ===== ORDERS =====
    public Order createOrder(int id, int tableNumber) {
        TableRestaurant table = tableService.findTableByNumber(tableNumber);
        if (table == null) return null;
        tableService.occupyTable(table);
        return orderService.createOrder(id, table);
    }

    public void addItemToOrder(int orderId, int menuItemId, int quantity) {
        Order order = orderService.findOrderById(orderId);
        MenuItem item = menuService.findItemById(menuItemId);
        if (order == null || item == null) return;
        OrderItem orderItem = new OrderItem(item, quantity);
        orderService.addItemToOrder(order, orderItem);
    }

    public void prepareOrder(int orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) order.prepare();
    }

    public void readyOrder(int orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) order.ready();
    }

    public void serveOrder(int orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) order.serve();
    }

    public void payOrder(int orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            order.pay();
            TableRestaurant table = order.getTable();
            if (table != null) tableService.freeTable(table);
        }
    }

    public void cancelOrder(int orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            order.cancel();
            TableRestaurant table = order.getTable();
            if (table != null) tableService.freeTable(table);
        }
    }

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public Order findOrder(int id) {
        return orderService.findOrderById(id);
    }

    // ===== EMPLOYEES =====
    public void addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // ===== RESERVATIONS =====
    public void addReservation(String customerName, String phone, int tableNumber) {
        Customer customer = new Customer(customerName, phone);
        TableRestaurant table = tableService.findTableByNumber(tableNumber);
        if (table == null) return;
        Reservation reservation = new Reservation(customer, table, LocalDateTime.now());
        reservationService.addReservation(reservation);
        tableService.occupyTable(table);
    }

    public List<Reservation> getAllReservations() {
        return reservationService.getReservations();
    }
}