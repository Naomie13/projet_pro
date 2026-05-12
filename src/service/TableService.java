package service;

import model.Table;
import model.TableStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TableService {

    private List<Table> tables = new ArrayList<>();

    public void addTable(Table table) {
        tables.add(table);
    }

    public boolean removeTable(int number) {
        return tables.removeIf(t -> t.getNumber() == number);
    }

    public Optional<Table> getTable(int number) {
        return tables.stream()
                .filter(t -> t.getNumber() == number)
                .findFirst();
    }

    public List<Table> getAllTables() {
        return tables;
    }

    public List<Table> getAvailableTables() {
        List<Table> available = new ArrayList<>();
        for (Table t : tables) {
            if (t.isAvailable()) {
                available.add(t);
            }
        }
        return available;
    }

    public boolean setTableStatus(int number, TableStatus status) {
        Optional<Table> table = getTable(number);
        if (table.isPresent()) {
            table.get().setStatus(status);
            return true;
        }
        return false;
    }

    public void displayAllTables() {
        if (tables.isEmpty()) {
            System.out.println("Aucune table enregistrée.");
            return;
        }
        System.out.println("=== Tables ===");
        for (Table t : tables) {
            System.out.println(t);
        }
    }
}