package observer;

import model.Ingredient;

public class StockObserver {

    public void alertLowStock(Ingredient ingredient) {
        System.out.println("[STOCK] ⚠️ " + ingredient.getName() +
                " — stock bas! (" + ingredient.getQuantity() +
                " " + ingredient.getUnit() + " restants)");
    }
}