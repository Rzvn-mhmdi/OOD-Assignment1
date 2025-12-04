package services;
public class CardPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by card: " + amount);
    }
}
