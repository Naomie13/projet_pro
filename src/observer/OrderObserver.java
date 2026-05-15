package observer;

import model.Order;
import model.OrderStatus;

public interface OrderObserver {
    void update(Order order, OrderStatus status);
}