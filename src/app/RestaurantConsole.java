package app;

import java.time.LocalDateTime;
import java.util.Scanner;

import facade.RestaurantFacade;
import model.*;
import strategy.*;

public class RestaurantConsole {

    private Scanner scanner;
    private RestaurantFacade restaurant;

    public RestaurantConsole() {
        scanner = new Scanner(System.in);
        restaurant = new RestaurantFacade();
    }

    public void start() {
        int choix;
        do {
            afficherMenuPrincipal();
            choix = scanner.nextInt();
            switch (choix) {
                case 1: gererMenu(); break;
                case 2: gererCommandes(); break;
                case 3: gererTables(); break;
                case 4: gererPersonnel(); break;
                case 5: gererReservations(); break;
                case 6: gererStock(); break;
                case 7: gererRapports(); break;
                case 8: restaurant.saveAll(); break;
                case 0:
                    restaurant.saveAll();
                    System.out.println("Application fermée. À bientôt!");
                    break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void afficherMenuPrincipal() {
        System.out.println("\n========== SYSTÈME DE GESTION RESTAURANT ==========");
        System.out.println("1. Gestion du menu");
        System.out.println("2. Gestion des commandes");
        System.out.println("3. Gestion des tables");
        System.out.println("4. Gestion du personnel");
        System.out.println("5. Gestion des réservations");
        System.out.println("6. Gestion du stock");
        System.out.println("7. Rapports");
        System.out.println("8. Sauvegarder");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    // ================= MENU =================
    private void gererMenu() {
        int choix;
        do {
            System.out.println("\n--- GESTION DU MENU ---");
            System.out.println("1. Ajouter un plat");
            System.out.println("2. Ajouter une boisson");
            System.out.println("3. Afficher le menu");
            System.out.println("4. Supprimer un item");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: ajouterPlat(); break;
                case 2: ajouterBoisson(); break;
                case 3: afficherMenu(); break;
                case 4: supprimerItemMenu(); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void ajouterPlat() {
        scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String name = scanner.nextLine();
        System.out.print("Prix: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Épicé (true/false): ");
        boolean spicy = scanner.nextBoolean();
        restaurant.addFood(id, name, price, description, spicy);
        System.out.println("Plat ajouté avec succès.");
    }

    private void ajouterBoisson() {
        scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String name = scanner.nextLine();
        System.out.print("Prix: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Alcoolisé (true/false): ");
        boolean alcoholic = scanner.nextBoolean();
        restaurant.addDrink(id, name, price, description, alcoholic);
        System.out.println("Boisson ajoutée avec succès.");
    }

    private void afficherMenu() {
        if (restaurant.getMenu().isEmpty()) {
            System.out.println("Le menu est vide.");
            return;
        }
        for (MenuItem item : restaurant.getMenu()) {
            System.out.println(item);
        }
    }

    private void supprimerItemMenu() {
        System.out.print("ID de l'item à supprimer: ");
        int id = scanner.nextInt();
        restaurant.removeMenuItem(id);
        System.out.println("Item supprimé.");
    }

    // ================= COMMANDES =================
    private void gererCommandes() {
        int choix;
        do {
            System.out.println("\n--- GESTION DES COMMANDES ---");
            System.out.println("1. Créer une commande");
            System.out.println("2. Afficher les commandes");
            System.out.println("3. Ajouter un item à une commande");
            System.out.println("4. Mettre en préparation");
            System.out.println("5. Commande prête");
            System.out.println("6. Servir la commande");
            System.out.println("7. Payer la commande");
            System.out.println("8. Annuler la commande");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: creerCommande(); break;
                case 2: afficherCommandes(); break;
                case 3: ajouterItemCommande(); break;
                case 4: changerEtatCommande("prepare"); break;
                case 5: changerEtatCommande("ready"); break;
                case 6: changerEtatCommande("serve"); break;
                case 7: payerCommande(); break;
                case 8: changerEtatCommande("cancel"); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void creerCommande() {
        System.out.print("ID de la commande: ");
        int id = scanner.nextInt();
        System.out.print("Numéro de table: ");
        int tableNumber = scanner.nextInt();
        Order order = restaurant.createOrder(id, tableNumber);
        if (order == null) {
            System.out.println("Table introuvable.");
        } else {
            System.out.println("Commande créée avec succès.");
        }
    }

    private void afficherCommandes() {
        if (restaurant.getAllOrders().isEmpty()) {
            System.out.println("Aucune commande.");
            return;
        }
        for (Order order : restaurant.getAllOrders()) {
            System.out.println(order);
        }
    }

    private void ajouterItemCommande() {
        System.out.print("ID de la commande: ");
        int orderId = scanner.nextInt();
        System.out.print("ID de l'item du menu: ");
        int itemId = scanner.nextInt();
        System.out.print("Quantité: ");
        int quantity = scanner.nextInt();
        restaurant.addItemToOrder(orderId, itemId, quantity);
        System.out.println("Item ajouté à la commande.");
    }

    private void changerEtatCommande(String action) {
        System.out.print("ID de la commande: ");
        int orderId = scanner.nextInt();
        switch (action) {
            case "prepare": restaurant.prepareOrder(orderId); break;
            case "ready":   restaurant.readyOrder(orderId); break;
            case "serve":   restaurant.serveOrder(orderId); break;
            case "cancel":  restaurant.cancelOrder(orderId); break;
        }
    }

    private void payerCommande() {
        System.out.print("ID de la commande: ");
        int orderId = scanner.nextInt();
        System.out.println("Méthode de paiement:");
        System.out.println("1. Espèces (-2%)");
        System.out.println("2. Carte bancaire (+1.5%)");
        System.out.println("3. Paiement en ligne (+2.5%)");
        System.out.print("Votre choix: ");
        int choix = scanner.nextInt();
        PaymentStrategy strategy;
        switch (choix) {
            case 1: strategy = new CashStrategy(); break;
            case 2: strategy = new CardStrategy(); break;
            case 3: strategy = new OnlineStrategy(); break;
            default:
                System.out.println("Choix invalide.");
                return;
        }
        double total = restaurant.payOrder(orderId, strategy);
        System.out.println("Paiement effectué : " + String.format("%.2f", total) + " €");
    }

    // ================= TABLES =================
    private void gererTables() {
        int choix;
        do {
            System.out.println("\n--- GESTION DES TABLES ---");
            System.out.println("1. Ajouter une table");
            System.out.println("2. Afficher les tables");
            System.out.println("3. Occuper une table");
            System.out.println("4. Libérer une table");
            System.out.println("5. Supprimer une table");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: ajouterTable(); break;
                case 2: afficherTables(); break;
                case 3: occuperTable(); break;
                case 4: libererTable(); break;
                case 5: supprimerTable(); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void ajouterTable() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Numéro de table: ");
        int number = scanner.nextInt();
        System.out.print("Capacité: ");
        int capacity = scanner.nextInt();
        restaurant.addTable(id, number, capacity);
        System.out.println("Table ajoutée avec succès.");
    }

    private void afficherTables() {
        if (restaurant.getAllTables().isEmpty()) {
            System.out.println("Aucune table enregistrée.");
            return;
        }
        for (TableRestaurant table : restaurant.getAllTables()) {
            System.out.println(table);
        }
    }

    private void occuperTable() {
        System.out.print("Numéro de table: ");
        int number = scanner.nextInt();
        restaurant.occupyTable(number);
        System.out.println("Table occupée.");
    }

    private void libererTable() {
        System.out.print("Numéro de table: ");
        int number = scanner.nextInt();
        restaurant.freeTable(number);
        System.out.println("Table libérée.");
    }

    private void supprimerTable() {
        System.out.print("ID de la table: ");
        int id = scanner.nextInt();
        restaurant.removeTable(id);
        System.out.println("Table supprimée.");
    }

    // ================= PERSONNEL =================
    private void gererPersonnel() {
        int choix;
        do {
            System.out.println("\n--- GESTION DU PERSONNEL ---");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Afficher le personnel");
            System.out.println("3. Affecter un serveur à une table");
            System.out.println("4. Supprimer un employé");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: ajouterEmploye(); break;
                case 2: afficherPersonnel(); break;
                case 3: affecterServeur(); break;
                case 4: supprimerEmploye(); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void ajouterEmploye() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String name = scanner.nextLine();
        Waiter waiter = new Waiter(id, name);
        restaurant.addEmployee(waiter);
        System.out.println("Employé ajouté avec succès.");
    }

    private void afficherPersonnel() {
        if (restaurant.getAllEmployees().isEmpty()) {
            System.out.println("Aucun employé enregistré.");
            return;
        }
        for (Employee employee : restaurant.getAllEmployees()) {
            System.out.println(employee);
        }
    }

    private void affecterServeur() {
        System.out.print("ID du serveur: ");
        int employeeId = scanner.nextInt();
        System.out.print("Numéro de table: ");
        int tableNumber = scanner.nextInt();
        restaurant.assignWaiterToTable(employeeId, tableNumber);
    }

    private void supprimerEmploye() {
        System.out.print("ID de l'employé: ");
        int id = scanner.nextInt();
        restaurant.removeEmployee(id);
        System.out.println("Employé supprimé.");
    }

    // ================= RESERVATIONS =================
    private void gererReservations() {
        int choix;
        do {
            System.out.println("\n--- GESTION DES RÉSERVATIONS ---");
            System.out.println("1. Ajouter une réservation");
            System.out.println("2. Afficher les réservations");
            System.out.println("3. Annuler une réservation");
            System.out.println("4. Modifier une réservation");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: ajouterReservation(); break;
                case 2: afficherReservations(); break;
                case 3: annulerReservation(); break;
                case 4: modifierReservation(); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void ajouterReservation() {
        scanner.nextLine();
        System.out.print("Nom du client: ");
        String name = scanner.nextLine();
        System.out.print("Téléphone: ");
        String phone = scanner.nextLine();
        System.out.print("Numéro de table: ");
        int tableNumber = scanner.nextInt();
        restaurant.addReservation(name, phone, tableNumber);
        System.out.println("Réservation ajoutée avec succès.");
    }

    private void afficherReservations() {
        if (restaurant.getAllReservations().isEmpty()) {
            System.out.println("Aucune réservation.");
            return;
        }
        for (Reservation reservation : restaurant.getAllReservations()) {
            System.out.println(reservation);
        }
    }

    private void annulerReservation() {
        scanner.nextLine();
        System.out.print("Nom du client: ");
        String name = scanner.nextLine();
        restaurant.cancelReservation(name);
    }

    private void modifierReservation() {
        scanner.nextLine();
        System.out.print("Nom du client: ");
        String name = scanner.nextLine();
        System.out.print("Nouveau numéro de table: ");
        int tableNumber = scanner.nextInt();
        restaurant.modifyReservation(name, tableNumber, LocalDateTime.now());
        System.out.println("Réservation modifiée.");
    }

    // ================= STOCK =================
    private void gererStock() {
        int choix;
        do {
            System.out.println("\n--- GESTION DU STOCK ---");
            System.out.println("1. Ajouter un ingrédient");
            System.out.println("2. Afficher le stock");
            System.out.println("3. Consommer un ingrédient");
            System.out.println("4. Réapprovisionner");
            System.out.println("5. Afficher stock bas");
            System.out.println("6. Supprimer un ingrédient");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: ajouterIngredient(); break;
                case 2: restaurant.getAllIngredients().forEach(System.out::println); break;
                case 3: consommerIngredient(); break;
                case 4: reapprovisionner(); break;
                case 5: restaurant.getLowStockIngredients().forEach(System.out::println); break;
                case 6: supprimerIngredient(); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void ajouterIngredient() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom: ");
        String name = scanner.nextLine();
        System.out.print("Quantité initiale: ");
        double quantity = scanner.nextDouble();
        System.out.print("Quantité minimum: ");
        double min = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Unité (kg/L/pcs): ");
        String unit = scanner.nextLine();
        restaurant.addIngredient(new Ingredient(id, name, quantity, min, unit));
        System.out.println("Ingrédient ajouté.");
    }

    private void consommerIngredient() {
        System.out.print("ID de l'ingrédient: ");
        int id = scanner.nextInt();
        System.out.print("Quantité à consommer: ");
        double amount = scanner.nextDouble();
        restaurant.consumeIngredient(id, amount);
    }

    private void reapprovisionner() {
        System.out.print("ID de l'ingrédient: ");
        int id = scanner.nextInt();
        System.out.print("Quantité à ajouter: ");
        double amount = scanner.nextDouble();
        restaurant.restockIngredient(id, amount);
    }

    private void supprimerIngredient() {
        System.out.print("ID de l'ingrédient: ");
        int id = scanner.nextInt();
        restaurant.removeIngredient(id);
        System.out.println("Ingrédient supprimé.");
    }

    // ================= RAPPORTS =================
    private void gererRapports() {
        int choix;
        do {
            System.out.println("\n--- RAPPORTS ---");
            System.out.println("1. Rapport des ventes");
            System.out.println("2. Plats les plus commandés");
            System.out.println("3. Rapport des tables");
            System.out.println("4. Rapport du stock");
            System.out.println("5. Rapport complet");
            System.out.println("0. Retour");
            choix = scanner.nextInt();
            switch (choix) {
                case 1: restaurant.generateSalesReport(); break;
                case 2: restaurant.generatePopularItemsReport(); break;
                case 3: restaurant.generateTableReport(); break;
                case 4: restaurant.generateStockReport(); break;
                case 5: restaurant.generateFullReport(); break;
                case 0: break;
                default: System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }
}