package services;

import models.Customer;
import models.Room;

public class Reservation {

    private Room room;
    private Customer customer;
    private int nights;

    public Reservation(Room r, Customer c, int nights) {
        this.room = r;
        this.customer = c;
        this.nights = nights;
    }
    
    public double totalPrice(){
        return room.getPrice() * nights;
    }

    public void applyDiscountToRoom(double factor) {
        room.applyDiscount(factor);
    }

    public boolean isCustomerFromCity(String city) {
        return customer.isCity(city);
    }

    public String getCustomerEmail() { return customer.getEmail(); }
    public String getCustomerName() { return customer.getName(); }
    public String getRoomNumber() { return room.getNumber(); }
    public String getRoomType() { return room.getType(); }
}
