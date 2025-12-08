package services;

public class ReservationService {
    private final MessageSender messageSender;
    private final PaymentService paymentService;
    private final DiscountStrategy discountStrategy;
    private final InvoicePrinter invoicePrinter;

    public ReservationService(
        MessageSender sender,
        PaymentService paymentProcessor,
        DiscountStrategy discount,
        InvoicePrinter printer)
    {
        this.messageSender = sender;
        this.paymentService = paymentProcessor;
        this.discountStrategy = discount;
        this.invoicePrinter = printer;
    }

    public void makeReservation(Reservation res, String confirmationMessage){
        System.out.println("Processing reservation for " + res.getCustomerName());

        discountStrategy.applyDiscount(res);

        paymentService.processPayment(res.getTotalPrice());

        invoicePrinter.printInvoice(res);

        messageSender.sendMessage(res.getCustomerEmail(), confirmationMessage);
    }
}