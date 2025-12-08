package services;

public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by PayPal: " + amount);
    }
}
