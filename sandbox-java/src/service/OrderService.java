package service;

import payment.PaymentMethod;

public class OrderService {
    private final PaymentMethod paymentMethod;

    public OrderService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void checkout(double amount) {
        boolean success = paymentMethod.pay(amount);

        if (success) {
            System.out.println("Commande validée");
        } else {
            System.out.println("Paiement refusé");
        }
    }
}
