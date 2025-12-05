import services.*; 
import models.Customer;
import models.LuxuryRoom;
import models.Room;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Customer customer = new Customer("Ali", "ali@example.com","09124483765", "Paris");
        Room room = new LuxuryRoom("203", 150);
        Reservation res = new Reservation(room, customer, 2);
        DiscountStrategy parisDiscount = new ParisDiscountStrategy();
        List<DiscountStrategy> allDiscounts = List.of(parisDiscount); 
        DiscountStrategy compositeDiscountService = new CompositeDiscountService(allDiscounts);
        PaymentService paypalProcessor = new PayPalPaymentService(); 
        MessageSender emailSender = new EmailSender();
        InvoicePrinter printer = new InvoicePrinter(); 
        ReservationService service = new ReservationService();
        service.makeReservation(
            res, 
            paypalProcessor,         
            emailSender,              
            compositeDiscountService, 
            printer                   
        );
    }
}
