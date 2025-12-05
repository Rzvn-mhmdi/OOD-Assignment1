package services;

public class ParisDiscountStrategy implements DiscountStrategy {
    @Override
    public void applyDiscount(Reservation res) {
        if (res.customer.city.equals("Paris")) {
            System.out.println("Apply city discount for Paris!");
            res.room.price *= 0.9;
        }
    }
}
