package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private TableRestaurant table;
    private List<OrderItem> items;
    private OrderStatus status;

    // Constructeur
    public Order(int id, TableRestaurant table) {

        this.id = id;
        this.table = table;
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
    }

    // Getter ID
    public int getId() {
        return id;
    }

    // Getter Table
    public TableRestaurant getTable() {
        return table;
    }

    // Setter Table
    public void setTable(TableRestaurant table) {
        this.table = table;
    }

    // Getter Items
    public List<OrderItem> getItems() {
        return items;
    }

    // Getter Status
    public OrderStatus getStatus() {
        return status;
    }

    // Setter Status
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Ajouter un item
    public void addItem(OrderItem item) {
        items.add(item);
    }

    // Supprimer un item
    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    // Calculer le total
    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    // Affichage
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("=================================\n");
        builder.append("ORDER #").append(id).append("\n");
        builder.append("=================================\n");

        if (table != null) {
            builder.append("Table : ")
                    .append(table.getTableNumber())
                    .append("\n");
        }

        builder.append("Status : ")
                .append(status)
                .append("\n\n");

        builder.append("Items : \n");

        for (OrderItem item : items) {
            builder.append("- ")
                    .append(item)
                    .append("\n");
        }

        builder.append("\nTotal : ")
                .append(calculateTotal())
                .append(" €\n");

        return builder.toString();
    }
}