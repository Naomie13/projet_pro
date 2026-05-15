package state;

import model.Order;
import model.OrderStatus;

public class NewState implements OrderState {

    @Override
    public void prepare(Order order) {
        System.out.println("Commande #" + order.getId() + " en préparation...");
        order.setState(new PreparingState());
    }

    @Override
    public void ready(Order order) {
        System.out.println("Erreur : la commande doit d'abord être en préparation.");
    }

    @Override
    public void serve(Order order) {
        System.out.println("Erreur : la commande doit d'abord être en préparation.");
    }

    @Override
    public void pay(Order order) {
        System.out.println("Erreur : la commande doit d'abord être en préparation.");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Commande #" + order.getId() + " annulée.");
        order.setState(new CancelledState());
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.NEW;
    }
}