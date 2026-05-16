package service;

import model.TableRestaurant;

import java.util.ArrayList;
import java.util.List;


public class TableRestaurantService {

    private List<TableRestaurant> tables;

    // Constructeur
    public TableRestaurantService() {

        tables = new ArrayList<>();
    }

    // Ajouter une table
    public void addTable(TableRestaurant table) {

        tables.add(table);
    }

    // Supprimer une table
    public void removeTable(TableRestaurant table) {

        tables.remove(table);
    }
    
    public void removeTableById(int id) {
        tables.removeIf(t -> t.getId() == id);
    }

    // Rechercher une table par numéro
    public TableRestaurant findTableByNumber(int tableNumber) {

        for (TableRestaurant table : tables) {

            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }

        return null;
    }

    // Afficher toutes les tables
    public List<TableRestaurant> getAllTables() {

        return tables;
    }

    // Vérifier si une table est disponible
    public boolean isTableAvailable(TableRestaurant table) {

        return table.isStatus();
    }

    // Occuper une table
    public void occupyTable(TableRestaurant table) {

        table.setStatus(false);
    }

    // Libérer une table
    public void freeTable(TableRestaurant table) {

        table.setStatus(true);
    }
}