package strategy;

public class CardStrategy implements PaymentStrategy {

    private static final double FEE = 0.015;

    @Override
    public double processPayment(double amount) {
        double fee = amount * FEE;
        double total = amount + fee;
        System.out.println("[PAIEMENT CARTE]");
        System.out.println("Montant original : " + amount + " €");
        System.out.println("Frais bancaires (1.5%) : +" + String.format("%.2f", fee) + " €");
        System.out.println("Total final : " + String.format("%.2f", total) + " €");
        return total;
    }

    @Override
    public String getPaymentMethod() {
        return "Carte bancaire";
    }
}