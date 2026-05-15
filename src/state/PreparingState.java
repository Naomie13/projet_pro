package state;

import model.Order;
import model.OrderStatus;

public class PreparingState implements OrderState {

    @Override
    public void prepare(Order order) {
        System.out.println("Erreur : la commande est déjà en préparation.");
    }

    @Override
    public void ready(Order order) {
        System.out.println("Commande #" + order.getId() + " est prête !");
        order.setState(new ReadyState());
    }

    @Override
    public void serve(Order order) {
        System.out.println("Erreur : la commande doit d'abord être prête.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Erreur : la commande doit d'abord être servie.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Commande #" + order.getId() + " annulée.");
        order.setState(new CancelledState());
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.PREPARING;
    }
}