package service;

import model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private List<MenuItem> menu = new ArrayList<>();

    public void addItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
}