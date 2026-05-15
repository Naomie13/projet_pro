package service;

import model.Ingredient;
import observer.StockObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockService {

    private List<Ingredient> ingredients;
    private StockObserver stockObserver;

    public StockService() {
        this.ingredients = new ArrayList<>();
        this.stockObserver = new StockObserver();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(int id) {
        ingredients.removeIf(i -> i.getId() == id);
    }

    public Optional<Ingredient> findById(int id) {
        return ingredients.stream()
                .filter(i -> i.getId() == id)
                .findFirst();
    }

    public List<Ingredient> getAllIngredients() {
        return ingredients;
    }

    public List<Ingredient> getLowStockIngredients() {
        List<Ingredient> low = new ArrayList<>();
        for (Ingredient i : ingredients) {
            if (i.isLowStock()) low.add(i);
        }
        return low;
    }

    public void consume(int id, double amount) {
        Optional<Ingredient> opt = findById(id);
        if (!opt.isPresent()) {
            System.out.println("Ingrédient introuvable.");
            return;
        }
        Ingredient ingredient = opt.get();
        if (ingredient.getQuantity() < amount) {
            System.out.println("[STOCK] ⚠️ Stock insuffisant pour: " + ingredient.getName());
            return;
        }
        ingredient.setQuantity(ingredient.getQuantity() - amount);
        System.out.println("[STOCK] " + ingredient.getName() +
                " consommé: -" + amount + " " + ingredient.getUnit() +
                " (restant: " + ingredient.getQuantity() + ")");
        if (ingredient.isLowStock()) {
            stockObserver.alertLowStock(ingredient);
        }
    }

    public void restock(int id, double amount) {
        Optional<Ingredient> opt = findById(id);
        if (!opt.isPresent()) {
            System.out.println("Ingrédient introuvable.");
            return;
        }
        Ingredient ingredient = opt.get();
        ingredient.setQuantity(ingredient.getQuantity() + amount);
        System.out.println("[STOCK] " + ingredient.getName() +
                " réapprovisionné: +" + amount + " " + ingredient.getUnit() +
                " (total: " + ingredient.getQuantity() + ")");
    }

    public void displayAllIngredients() {
        if (ingredients.isEmpty()) {
            System.out.println("Aucun ingrédient enregistré.");
            return;
        }
        System.out.println("=== STOCK ===");
        for (Ingredient i : ingredients) {
            System.out.println(i);
        }
    }
}