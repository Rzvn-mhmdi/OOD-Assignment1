package services;

public class ReservationService {
    
    public void makeReservation(Reservation res, PaymentService payment,
        MessageSender notifier, DiscountStrategy discount, InvoicePrinter printer){
        System.out.println("Processing reservation for " + res.customer.name);
        discount.applyDiscount(res);
        payment.processPayment(res.totalPrice());
        printer.printInvoice(res);
        notifier.sendNotification(res.customer.email, "Your reservation confirmed!");
    }
}
