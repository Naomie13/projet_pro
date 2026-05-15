package state;

import model.Order;
import model.OrderStatus;

public class PaidState implements OrderState {

    @Override
    public void prepare(Order order) {
        System.out.println("Erreur : la commande est déjà payée.");
    }

    @Override
    public void ready(Order order) {
        System.out.println("Erreur : la commande est déjà payée.");
    }

    @Override
    public void serve(Order order) {
        System.out.println("Erreur : la commande est déjà payée.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Erreur : la commande est déjà payée.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Erreur : impossible d'annuler une commande déjà payée.");
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.PAID;
    }
}