package facade;

import factory.MenuItemFactory;
import repository.*;

import model.*;
import service.*;
import observer.*;
import strategy.*;


import java.time.LocalDateTime;
import java.util.List;

public class RestaurantFacade {

    private MenuService menuService;
    private OrderService orderService;
    private TableRestaurantService tableService;
    private EmployeeService employeeService;
    private ReservationService reservationService;
    private StockService stockService;
    private ReportService reportService;
    private SaveService saveService;
    private DatabaseRepository dbRepository;
    
    
    public RestaurantFacade() {
        this.menuService = new MenuService();
        this.orderService = new OrderService();
        this.tableService = new TableRestaurantService();
        this.employeeService = new EmployeeService();
        this.reservationService = new ReservationService();
        this.stockService = new StockService();
        this.saveService = new SaveService();
        this.reportService = new ReportService(
            orderService.getAllOrders(),
            menuService.getAllItems(),
            tableService.getAllTables(),
            stockService.getAllIngredients()
        );
        this.dbRepository = new DatabaseRepository();
        loadData();
    }

    private void loadData() {
        // Essayer DB d'abord
        List<TableRestaurant> tables = dbRepository.loadTables();
        if (!tables.isEmpty()) {
            tables.forEach(t -> {
                if (tableService.findTableByNumber(t.getTableNumber()) == null) {
                    tableService.addTable(t);
                }
            });
            System.out.println("[DB] " + tables.size() + " tables chargées.");
        } else {
            // Fallback Gson
            List<TableRestaurant> jsonTables = saveService.loadTables();
            jsonTables.forEach(t -> {
                if (tableService.findTableByNumber(t.getTableNumber()) == null) {
                    tableService.addTable(t);
                }
            });
            if (!jsonTables.isEmpty())
                System.out.println("[JSON] " + jsonTables.size() + " tables chargées.");
        }

        List<MenuItem> menuItems = dbRepository.loadMenuItems();
        if (!menuItems.isEmpty()) {
            menuItems.forEach(item -> menuService.addItem(item));
            System.out.println("[DB] " + menuItems.size() + " items chargés.");
        } else {
            List<MenuItem> jsonItems = saveService.loadMenu();
            jsonItems.forEach(item -> menuService.addItem(item));
            if (!jsonItems.isEmpty())
                System.out.println("[JSON] " + jsonItems.size() + " items chargés.");
        }

        List<Ingredient> ingredients = dbRepository.loadIngredients();
        if (!ingredients.isEmpty()) {
            ingredients.forEach(i -> {
                if (!stockService.findById(i.getId()).isPresent()) {
                    stockService.addIngredient(i);
                }
            });
            System.out.println("[DB] " + ingredients.size() + " ingrédients chargés.");
        } else {
            List<Ingredient> jsonIngredients = saveService.loadIngredients();
            jsonIngredients.forEach(i -> stockService.addIngredient(i));
            if (!jsonIngredients.isEmpty())
                System.out.println("[JSON] " + jsonIngredients.size() + " ingrédients chargés.");
        }

        List<Employee> employees = dbRepository.loadEmployees();
        if (!employees.isEmpty()) {
            employees.forEach(e -> employeeService.addEmployee(e));
            System.out.println("[DB] " + employees.size() + " employés chargés.");
        }
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
        Order order = orderService.createOrder(id, table);
        order.addObserver(new KitchenObserver()); 
        order.addObserver(new WaiterObserver());
        return order;
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

    public double payOrder(int orderId, PaymentStrategy strategy) {
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            System.out.println("Commande introuvable.");
            return 0;
        }
        if (order.getStatus() == OrderStatus.PAID) {
            System.out.println("Erreur : la commande est déjà payée.");
            return 0;
        }
        if (order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Erreur : la commande est annulée.");
            return 0;
        }
        double amount = order.calculateTotal();
        double finalAmount = strategy.processPayment(amount);
        order.pay();
        TableRestaurant table = order.getTable();
        if (table != null) tableService.freeTable(table);
        return finalAmount;
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
    
    public void addIngredient(Ingredient ingredient) {
        stockService.addIngredient(ingredient);
    }

    public void consumeIngredient(int id, double amount) {
        stockService.consume(id, amount);
    }

    public void restockIngredient(int id, double amount) {
        stockService.restock(id, amount);
    }

    public List<Ingredient> getAllIngredients() {
        return stockService.getAllIngredients();
    }

    public List<Ingredient> getLowStockIngredients() {
        return stockService.getLowStockIngredients();
    }
    
    public void generateSalesReport() { reportService.generateSalesReport(); }
    public void generatePopularItemsReport() { reportService.generatePopularItemsReport(); }
    public void generateTableReport() { reportService.generateTableReport(); }
    public void generateStockReport() { reportService.generateStockReport(); }
    public void generateFullReport() { reportService.generateFullReport(); }
    
    public void saveAll() {
        // Gson backup
        saveService.saveAll(
            menuService.getAllItems(),
            tableService.getAllTables(),
            stockService.getAllIngredients()
        );
        // DB principale
        menuService.getAllItems().forEach(item -> dbRepository.saveMenuItem(item));
        tableService.getAllTables().forEach(table -> dbRepository.saveTable(table));
        stockService.getAllIngredients().forEach(i -> dbRepository.saveIngredient(i));
        employeeService.getAllEmployees().forEach(e -> dbRepository.saveEmployee(e));
        System.out.println("[DB] Sauvegarde DB complète.");
    }

    public List<TableRestaurant> loadTables() {
        return saveService.loadTables();
    }

    public List<Ingredient> loadIngredients() {
        return saveService.loadIngredients();
    }
    
    public boolean assignWaiterToTable(int employeeId, int tableNumber) {
        TableRestaurant table = tableService.findTableByNumber(tableNumber);
        if (table == null) {
            System.out.println("Table introuvable.");
            return false;
        }
        boolean result = employeeService.assignWaiterToTable(employeeId, tableNumber);
        if (result) {
            // Sauvegarder immédiatement en DB
            Employee employee = employeeService.findEmployeeById(employeeId);
            if (employee != null) dbRepository.saveEmployee(employee);
        }
        return result;
    }
    
    public boolean cancelReservation(String customerName) {
        return reservationService.cancelReservation(customerName);
    }

    public boolean modifyReservation(String customerName, int newTableNumber, LocalDateTime newDateTime) {
        TableRestaurant table = tableService.findTableByNumber(newTableNumber);
        if (table == null) {
            System.out.println("Table introuvable.");
            return false;
        }
        return reservationService.modifyReservation(customerName, table, newDateTime);
    }
    
    
    
}