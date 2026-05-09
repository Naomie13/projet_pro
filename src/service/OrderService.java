package service;

import model.Order;
import model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders = new ArrayList<>();

    // créer commande
    public Order createOrder(int id) {
        Order order = new Order(id);
        orders.add(order);
        return order;
    }

    // ajouter item
    public void addItemToOrder(Order order, OrderItem item) {
        order.addItem(item);
    }

    // calcul total
    public double getOrderTotal(Order order) {
        return order.calculateTotal();
    }

    // afficher toutes les commandes
    public List<Order> getAllOrders() {
        return orders;
    }
}