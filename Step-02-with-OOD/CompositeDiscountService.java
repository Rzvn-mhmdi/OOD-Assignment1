package services;

import java.util.List;

public class CompositeDiscountService implements DiscountStrategy {
    private final List<DiscountStrategy> allStrategies;

    public CompositeDiscountService(List<DiscountStrategy> allStrategies) {
        this.allStrategies = allStrategies;
    }

    @Override
    public void applyDiscount(Reservation res) {
        for (DiscountStrategy strategy : allStrategies) {
            strategy.applyDiscount(res);
        }
    }
}
