package services;

public interface PaymentService {
    void processPayment(double amount);
}

public class CardPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by card: " + amount);
    }
}

public class CashPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by cash: " + amount);
    }
}

public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid by PayPal: " + amount);
    }
}
