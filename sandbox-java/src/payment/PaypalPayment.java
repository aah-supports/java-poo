package payment;

public class PaypalPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paiement de " + amount + "€ avec PayPal");
        return true;
    }
}
