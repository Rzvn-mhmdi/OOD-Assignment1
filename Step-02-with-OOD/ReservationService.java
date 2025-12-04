package services;

import constants.Notifier;
import constants.PaymentMethods;

public class ReservationService {
    private Notifier notifier = Notifier.EMAIL; //default Notifier

    public void makeReservation(Reservation res, PaymentMethods paymentType, Notifier notifier){
        System.out.println("Processing reservation for " + res.customer.name);

        if(res.customer.city.equals("Paris")){
            System.out.println("Apply city discount for Paris!");
            res.room.price *= 0.9;
        }
        PaymentService processor;
        switch (paymentType){
            case CARD:
                processor = new CardPaymentService();
                break;
            case PAYPAL:
                processor = new PayPalPaymentService();
                break;
            case CASH:
                processor = new CashPaymentService();
                break;
        }
        processor.processPayment(res.totalPrice());

        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.customer.name);
        System.out.println("hotel.Room: " + res.room.number + " (" + res.room.type + ")");
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

       switch (this.notifier){
           case EMAIL :
           EmailSender emailSender = new EmailSender();
           emailSender.sendEmail(res.customer.email, "Your reservation confirmed!");
           break;
           default:
               System.out.println("There is no Message Provider");
       }
    }
}
