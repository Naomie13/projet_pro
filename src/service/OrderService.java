package service;

import model.Order;
import model.OrderItem;
import model.TableRestaurant;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public Order createOrder(int id, TableRestaurant table) {
        Order order = new Order(id, table);
        orders.add(order);
        return order;
    }

    public void addItemToOrder(Order order, OrderItem item) {
        order.addItem(item);
    }

    public void removeItemFromOrder(Order order, OrderItem item) {
        order.removeItem(item);
    }

    public double getOrderTotal(Order order) {
        return order.calculateTotal();
    }

    public Order findOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}