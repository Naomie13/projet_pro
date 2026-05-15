package observer;

import model.Order;
import model.OrderStatus;

public class WaiterObserver implements OrderObserver {

    @Override
    public void update(Order order, OrderStatus status) {
        switch (status) {
            case READY:
                System.out.println("[SERVEUR] Commande #" + order.getId() + " prête — à livrer table #" + order.getTable().getTableNumber());
                break;
            case SERVED:
                System.out.println("[SERVEUR] Commande #" + order.getId() + " servie.");
                break;
            case PAID:
                System.out.println("[SERVEUR] Table #" + order.getTable().getTableNumber() + " libérée — prête pour nouveaux clients.");
                break;
            default:
                break;
        }
    }
}