package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveService {

    private static final String DATA_DIR = "data/";
    private static final String MENU_FILE = DATA_DIR + "menu.json";
    private static final String TABLES_FILE = DATA_DIR + "tables.json";
    private static final String INGREDIENTS_FILE = DATA_DIR + "ingredients.json";

    private Gson gson;

    public SaveService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        new File(DATA_DIR).mkdirs();
    }

    // ===== SAVE =====
    public void saveMenu(List<MenuItem> items) {
        List<FoodItem> foods = new ArrayList<>();
        List<DrinkItem> drinks = new ArrayList<>();
        
        for (MenuItem item : items) {
            if (item instanceof FoodItem) foods.add((FoodItem) item);
            else if (item instanceof DrinkItem) drinks.add((DrinkItem) item);
        }
        
        writeFile(DATA_DIR + "foods.json", foods);
        writeFile(DATA_DIR + "drinks.json", drinks);
        System.out.println("[SAVE] Menu sauvegardé.");
    }

    public List<MenuItem> loadMenu() {
        List<MenuItem> all = new ArrayList<>();
        
        Type foodType = new TypeToken<List<FoodItem>>(){}.getType();
        Type drinkType = new TypeToken<List<DrinkItem>>(){}.getType();
        
        List<FoodItem> foods = readFile(DATA_DIR + "foods.json", foodType);
        List<DrinkItem> drinks = readFile(DATA_DIR + "drinks.json", drinkType);
        
        all.addAll(foods);
        all.addAll(drinks);
        return all;
    }
    public void saveTables(List<TableRestaurant> tables) {
        writeFile(TABLES_FILE, tables);
        System.out.println("[SAVE] Tables sauvegardées.");
    }

    public void saveIngredients(List<Ingredient> ingredients) {
        writeFile(INGREDIENTS_FILE, ingredients);
        System.out.println("[SAVE] Stock sauvegardé.");
    }

    public void saveAll(List<MenuItem> items, List<TableRestaurant> tables, List<Ingredient> ingredients) {
        saveMenu(items);
        saveTables(tables);
        saveIngredients(ingredients);
        System.out.println("[SAVE] ✅ Sauvegarde complète.");
    }

    // ===== LOAD =====
    public List<FoodItem> loadFoodItems() {
        Type type = new TypeToken<List<FoodItem>>(){}.getType();
        return readFile(MENU_FILE + ".food", type);
    }

    public List<TableRestaurant> loadTables() {
        Type type = new TypeToken<List<TableRestaurant>>(){}.getType();
        return readFile(TABLES_FILE, type);
    }

    public List<Ingredient> loadIngredients() {
        Type type = new TypeToken<List<Ingredient>>(){}.getType();
        return readFile(INGREDIENTS_FILE, type);
    }

    // ===== HELPERS =====
    private void writeFile(String path, Object data) {
        try (Writer writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("[SAVE] Erreur sauvegarde: " + e.getMessage());
        }
    }

    private <T> List<T> readFile(String path, Type type) {
        File file = new File(path);
        if (!file.exists()) return new ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            List<T> result = gson.fromJson(reader, type);
            return result != null ? result : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("[SAVE] Erreur chargement: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}