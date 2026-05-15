package model;

import state.OrderState;
import state.NewState;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private TableRestaurant table;
    private List<OrderItem> items;
    private OrderState currentState;

    public Order(int id, TableRestaurant table) {
        this.id = id;
        this.table = table;
        this.items = new ArrayList<>();
        this.currentState = new NewState();
    }

    public int getId() {
        return id;
    }

    public TableRestaurant getTable() {
        return table;
    }

    public void setTable(TableRestaurant table) {
        this.table = table;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return currentState.getStatus();
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }

    public void prepare() {
        currentState.prepare(this);
    }

    public void ready() {
        currentState.ready(this);
    }

    public void serve() {
        currentState.serve(this);
    }

    public void pay() {
        currentState.pay(this);
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
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
        builder.append("=================================\n");
        builder.append("ORDER #").append(id).append("\n");
        builder.append("=================================\n");
        if (table != null) {
            builder.append("Table : ").append(table.getTableNumber()).append("\n");
        }
        builder.append("Status : ").append(getStatus()).append("\n\n");
        builder.append("Items : \n");
        for (OrderItem item : items) {
            builder.append("- ").append(item).append("\n");
        }
        builder.append("\nTotal : ").append(calculateTotal()).append(" €\n");
        return builder.toString();
    }
}