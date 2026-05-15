package state;

import model.Order;
import model.OrderStatus;

public class CancelledState implements OrderState {

    @Override
    public void prepare(Order order) {
        System.out.println("Erreur : la commande est annulée.");
    }

    @Override
    public void ready(Order order) {
        System.out.println("Erreur : la commande est annulée.");
    }

    @Override
    public void serve(Order order) {
        System.out.println("Erreur : la commande est annulée.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Erreur : la commande est annulée.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Erreur : la commande est déjà annulée.");
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.CANCELLED;
    }
}