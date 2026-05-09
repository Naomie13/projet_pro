package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(int id) {

        this.id = id;
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Order #")
                .append(id)
                .append("\n");

        builder.append("Status: ")
                .append(status)
                .append("\n");

        builder.append("Items:\n");

        for (OrderItem item : items) {
            builder.append(item).append("\n");
        }

        builder.append("Total: ")
                .append(calculateTotal())
                .append(" €");

        return builder.toString();
    }
}