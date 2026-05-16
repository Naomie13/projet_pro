package model;

public class Waiter extends Employee {

    private int assignedTableNumber;

    public Waiter(int id, String name) {
        super(id, name);
        this.assignedTableNumber = -1;
    }

    public int getAssignedTableNumber() {
        return assignedTableNumber;
    }

    public void setAssignedTableNumber(int tableNumber) {
        this.assignedTableNumber = tableNumber;
    }

    public boolean isAssigned() {
        return assignedTableNumber != -1;
    }

    @Override
    public void work() {
        System.out.println(name + " is serving customers.");
    }

    @Override
    public String toString() {
        return "Waiter #" + id + " - " + name +
               " | Table: " + (isAssigned() ? "#" + assignedTableNumber : "non assigné");
    }
}