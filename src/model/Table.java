package model;

public class Table {

    public static int MAX_CAPACITY = 20;

    private int number;
    private int capacity;
    private TableStatus status;
    private int currentOrderId;

    public Table(int number, int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("Capacité ne peut pas dépasser " + MAX_CAPACITY);
        }
        this.number = number;
        this.capacity = capacity;
        this.status = TableStatus.LIBRE;
        this.currentOrderId = -1;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(int currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public boolean isAvailable() {
        return status == TableStatus.LIBRE;
    }

    @Override
    public String toString() {
        return "Table #" + number +
               " | Capacité: " + capacity +
               " | Statut: " + status +
               " | Commande: " + (currentOrderId == -1 ? "aucune" : "#" + currentOrderId);
    }
}