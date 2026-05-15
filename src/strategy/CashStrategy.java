package strategy;

public class CashStrategy implements PaymentStrategy {

    private static final double DISCOUNT = 0.02;

    @Override
    public double processPayment(double amount) {
        double discount = amount * DISCOUNT;
        double total = amount - discount;
        System.out.println("[PAIEMENT ESPÈCES]");
        System.out.println("Montant original : " + amount + " €");
        System.out.println("Réduction espèces (2%) : -" + String.format("%.2f", discount) + " €");
        System.out.println("Total final : " + String.format("%.2f", total) + " €");
        return total;
    }

    @Override
    public String getPaymentMethod() {
        return "Espèces";
    }
}