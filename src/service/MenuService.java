package service;

import model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private List<MenuItem> items;

    public MenuService() {
        items = new ArrayList<>();
    }

    public MenuItem findById(int id) {

        for (MenuItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void updateItem(MenuItem item) {

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == item.getId()) {
                items.set(i, item);
                return;
            }
        }
    }

    public List<MenuItem> getAll() {
        return items;
    }
}