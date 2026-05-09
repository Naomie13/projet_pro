package app;

import model.*;
import service.*;

public class Main {

    public static void main(String[] args) {

        // SERVICES
        OrderService orderService = new OrderService();

        // MENU
        FoodItem pizza = new FoodItem(1, "Pizza", 12, "Fromage", false);
        DrinkItem cola = new DrinkItem(2, "Coca", 2, "Boisson", false);

        // ORDER
        Order order = orderService.createOrder(1001);

        orderService.addItemToOrder(order, new OrderItem(pizza, 2));
        orderService.addItemToOrder(order, new OrderItem(cola, 3));

        // AFFICHAGE
        System.out.println(order);
    }
}