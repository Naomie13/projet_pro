package model;

public class Ingredient {

    private int id;
    private String name;
    private double quantity;
    private double minQuantity;
    private String unit;

    public Ingredient(int id, String name, double quantity, double minQuantity, String unit) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
        this.unit = unit;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getQuantity() { return quantity; }
    public double getMinQuantity() { return minQuantity; }
    public String getUnit() { return unit; }

    public void setQuantity(double quantity) { this.quantity = quantity; }

    public boolean isLowStock() {
        return quantity <= minQuantity;
    }

    @Override
    public String toString() {
        return "Ingredient: " + name +
               " | Quantité: " + quantity + " " + unit +
               " | Minimum: " + minQuantity + " " + unit +
               (isLowStock() ? " ⚠️ STOCK BAS" : "");
    }
}