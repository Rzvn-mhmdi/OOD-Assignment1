package services;
public class CashPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by cash: " + amount);
    }
}
