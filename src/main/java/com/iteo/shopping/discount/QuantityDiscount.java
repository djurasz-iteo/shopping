package com.iteo.shopping.discount;

import com.iteo.shopping.shared.Money;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

@RequiredArgsConstructor
public class QuantityDiscount implements Discount {

    private final UUID id;
    private final QuantityDiscountProperties properties;

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public Money calculateDiscountedPrice(Money price) {
        return price;
    }

    @Override
    public Money calculateDiscountedPrice(Money totalPrice, Integer quantity) {
        SortedSet<Integer> sortedSet = new TreeSet<>(properties.discountLevels().keySet()).descendingSet();
        for (Integer key : sortedSet) {
            if(quantity >= key) {
                Money toSubtract = totalPrice.percentage(properties.discountLevels().get(key));
                return totalPrice.subtract(toSubtract);
            }
        }

        return totalPrice;
    }

}

record QuantityDiscountProperties(Map<Integer, Integer> discountLevels) implements DiscountProperties {}
