package service;

import model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private List<MenuItem> menuItems;

    public MenuService() {
        menuItems = new ArrayList<>();
    }

    // ajouter item
    public void addItem(MenuItem item) {
        menuItems.add(item);
    }
    
    public void removeItemById(int id) {
        menuItems.removeIf(item -> item.getId() == id);
    }

    // afficher tous les items
    public List<MenuItem> getAllItems() {
        return menuItems;
    }

    // rechercher item par id
    public MenuItem findItemById(int id) {

        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    // supprimer item
    public void removeItem(MenuItem item) {
        menuItems.remove(item);
    }
}