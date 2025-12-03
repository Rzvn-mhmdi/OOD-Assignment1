package services;

import constants.Notifier;
import constants.PaymentMethods;

public class ReservationService {
    private MessageSender messageSender; 
    private PaymentProcessor paymentProcessor;

    public ReservationService(MessageSender sender, PaymentProcessor processor) {
        this.messageSender = sender;
        this.paymentProcessor = processor;
    }

    public void makeReservation(Reservation res, PaymentMethods paymentType, Notifier notifier){
        System.out.println("Processing reservation for " + res.getCustomerName());

        if(res.isCustomerFromCity("Paris")){
            System.out.println("Apply city discount for Paris!");
            res.applyDiscountToRoom(0.9); // Encapsulated discount application
        }

        switch (paymentType){
            case CARD:
                paymentProcessor.payByCard(res.totalPrice());
                break;
            case PAYPAL:
                paymentProcessor.payByPayPal(res.totalPrice());
                break;
            case CASH:
                paymentProcessor.payByCash(res.totalPrice());
                break;
        }

        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.getCustomerName());
        System.out.println("hotel.Room: " + res.getRoomNumber() + " (" + res.getRoomType() + ")");
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

       messageSender.sendMessage(res.getCustomerEmail(), "Your reservation confirmed!");
    }
}
