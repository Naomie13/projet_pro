package state;

import model.Order;
import model.OrderStatus;

public class ServedState implements OrderState {

    @Override
    public void prepare(Order order) {
        System.out.println("Erreur : la commande est déjà servie.");
    }

    @Override
    public void ready(Order order) {
        System.out.println("Erreur : la commande est déjà servie.");
    }

    @Override
    public void serve(Order order) {
        System.out.println("Erreur : la commande est déjà servie.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Commande #" + order.getId() + " payée !");
        order.setState(new PaidState());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Erreur : impossible d'annuler une commande déjà servie.");
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.SERVED;
    }
}