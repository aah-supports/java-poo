import payment.CreditCardPayment;
import payment.PaymentMethod;
import payment.PaypalPayment;
import service.OrderService;

public class Main {
    public static void main(String[] args) {
        PaymentMethod card = new CreditCardPayment();
        OrderService order1 = new OrderService(card);
        order1.checkout(49.99);

        System.out.println("-----");

        PaymentMethod paypal = new PaypalPayment();
        OrderService order2 = new OrderService(paypal);
        order2.checkout(79.90);
    }
}
