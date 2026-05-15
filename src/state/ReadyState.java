package state;

import model.Order;
import model.OrderStatus;

public class ReadyState implements OrderState {

    @Override
    public void prepare(Order order) {
        System.out.println("Erreur : la commande est déjà prête.");
    }

    @Override
    public void ready(Order order) {
        System.out.println("Erreur : la commande est déjà prête.");
    }

    @Override
    public void serve(Order order) {
        System.out.println("Commande #" + order.getId() + " servie !");
        order.setState(new ServedState());
    }

    @Override
    public void pay(Order order) {
        System.out.println("Erreur : la commande doit d'abord être servie.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Erreur : impossible d'annuler une commande déjà prête.");
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.READY;
    }
}