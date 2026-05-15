package strategy;

public class OnlineStrategy implements PaymentStrategy {

    private static final double FEE = 0.025;

    @Override
    public double processPayment(double amount) {
        double fee = amount * FEE;
        double total = amount + fee;
        System.out.println("[PAIEMENT EN LIGNE]");
        System.out.println("Montant original : " + amount + " €");
        System.out.println("Frais plateforme (2.5%) : +" + String.format("%.2f", fee) + " €");
        System.out.println("Total final : " + String.format("%.2f", total) + " €");
        return total;
    }

    @Override
    public String getPaymentMethod() {
        return "Paiement en ligne";
    }
}