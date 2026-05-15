package factory;

import model.DrinkItem;
import model.FoodItem;
import model.MenuItem;

public class MenuItemFactory {

    public static MenuItem create(String type, int id, String name, double price, String description, boolean extra) {
        switch (type.toLowerCase()) {
            case "food":
                return new FoodItem(id, name, price, description, extra);
            case "drink":
                return new DrinkItem(id, name, price, description, extra);
            default:
                throw new IllegalArgumentException("Type inconnu : " + type);
        }
    }
}