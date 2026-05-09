package payment;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paiement de " + amount + "€ par carte bancaire");
        return true;
    }
}
