package state;

import model.Order;
import model.OrderStatus;

public interface OrderState {
    void prepare(Order order);
    void ready(Order order);
    void serve(Order order);
    void pay(Order order);
    void cancel(Order order);
    OrderStatus getStatus();
}