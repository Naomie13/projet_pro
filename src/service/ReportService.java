package service;

import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    private List<Order> orders;
    private List<MenuItem> menuItems;
    private List<TableRestaurant> tables;
    private List<Ingredient> ingredients;

    public ReportService(List<Order> orders, List<MenuItem> menuItems,
                         List<TableRestaurant> tables, List<Ingredient> ingredients) {
        this.orders = orders;
        this.menuItems = menuItems;
        this.tables = tables;
        this.ingredients = ingredients;
    }

    // ===== RAPPORT VENTES =====
    public void generateSalesReport() {
        System.out.println("\n========== RAPPORT DE VENTES ==========");
        double totalRevenue = 0;
        int paidOrders = 0;
        int cancelledOrders = 0;

        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.PAID) {
                totalRevenue += order.calculateTotal();
                paidOrders++;
            } else if (order.getStatus() == OrderStatus.CANCELLED) {
                cancelledOrders++;
            }
        }

        System.out.println("Commandes totales     : " + orders.size());
        System.out.println("Commandes payées      : " + paidOrders);
        System.out.println("Commandes annulées    : " + cancelledOrders);
        System.out.println("Commandes en cours    : " + (orders.size() - paidOrders - cancelledOrders));
        System.out.println("Revenu total          : " + String.format("%.2f", totalRevenue) + " €");
        System.out.println("========================================");
    }

    // ===== PLATS LES PLUS COMMANDES =====
    public void generatePopularItemsReport() {
        System.out.println("\n========== PLATS LES PLUS COMMANDÉS ==========");

        Map<String, Integer> itemCount = new HashMap<>();

        for (Order order : orders) {
            for (OrderItem orderItem : order.getItems()) {
                String name = orderItem.getMenuItem().getName();
                int qty = orderItem.getQuantity();
                itemCount.put(name, itemCount.getOrDefault(name, 0) + qty);
            }
        }

        if (itemCount.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
            return;
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(itemCount.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.println(rank + ". " + entry.getKey() + " — " + entry.getValue() + " commandé(s)");
            rank++;
        }
        System.out.println("===============================================");
    }

    // ===== RAPPORT TABLES =====
    public void generateTableReport() {
        System.out.println("\n========== RAPPORT DES TABLES ==========");

        int free = 0, occupied = 0;
        for (TableRestaurant table : tables) {
            if (table.isStatus()) free++;
            else occupied++;
        }

        System.out.println("Tables totales    : " + tables.size());
        System.out.println("Tables libres     : " + free);
        System.out.println("Tables occupées   : " + occupied);
        System.out.println("=========================================");
    }

    // ===== RAPPORT STOCK =====
    public void generateStockReport() {
        System.out.println("\n========== RAPPORT DE STOCK ==========");

        List<Ingredient> lowStock = new ArrayList<>();
        for (Ingredient i : ingredients) {
            if (i.isLowStock()) lowStock.add(i);
        }

        System.out.println("Ingrédients totaux    : " + ingredients.size());
        System.out.println("Ingrédients stock bas : " + lowStock.size());

        if (!lowStock.isEmpty()) {
            System.out.println("\n⚠️  ALERTES STOCK BAS:");
            for (Ingredient i : lowStock) {
                System.out.println("   - " + i.getName() +
                        " : " + i.getQuantity() + " " + i.getUnit() +
                        " (min: " + i.getMinQuantity() + ")");
            }
        }
        System.out.println("=======================================");
    }

    // ===== RAPPORT COMPLET =====
    public void generateFullReport() {
        generateSalesReport();
        generatePopularItemsReport();
        generateTableReport();
        generateStockReport();
    }
}