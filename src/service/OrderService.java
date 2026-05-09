package service;

import model.Order;
import model.OrderItem;
import model.OrderStatus;
import model.TableRestaurant;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private List<Order> orders;

    // Constructeur
    public OrderService() {
        orders = new ArrayList<>();
    }

    // Créer une commande
    public Order createOrder(int id, TableRestaurant table) {

        Order order = new Order(id, table);

        orders.add(order);

        return order;
    }

    // Ajouter un item à une commande
    public void addItemToOrder(Order order,
                               OrderItem item) {

        order.addItem(item);
    }

    // Supprimer un item
    public void removeItemFromOrder(Order order,
                                    OrderItem item) {

        order.removeItem(item);
    }

    // Calculer le total
    public double getOrderTotal(Order order) {

        return order.calculateTotal();
    }

    // Modifier le statut
    public void updateOrderStatus(Order order,
                                  OrderStatus status) {

        order.setStatus(status);
    }

    // Rechercher une commande par ID
    public Order findOrderById(int id) {

        for (Order order : orders) {

            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    // Supprimer une commande
    public void removeOrder(Order order) {

        orders.remove(order);
    }

    // Afficher toutes les commandes
    public List<Order> getAllOrders() {

        return orders;
    }
}