package observer;

import model.Order;
import model.OrderStatus;

public class KitchenObserver implements OrderObserver {

    @Override
    public void update(Order order, OrderStatus status) {
        switch (status) {
            case PREPARING:
                System.out.println("[CUISINE] Commande #" + order.getId() + " reçue — début de préparation.");
                break;
            case READY:
                System.out.println("[CUISINE] Commande #" + order.getId() + " prête — en attente de service.");
                break;
            case CANCELLED:
                System.out.println("[CUISINE] Commande #" + order.getId() + " annulée.");
                break;
            default:
                break;
        }
    }
}