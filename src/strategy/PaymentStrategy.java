package strategy;

public interface PaymentStrategy {
    double processPayment(double amount);
    String getPaymentMethod();
}