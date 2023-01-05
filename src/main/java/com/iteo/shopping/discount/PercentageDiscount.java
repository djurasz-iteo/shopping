package com.iteo.shopping.discount;

import com.iteo.shopping.shared.Money;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PercentageDiscount implements Discount {

    private final UUID id;
    private final PercentageDiscountProperties configuration;

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public Money calculateDiscountedPrice(Money price) {
        return price.percentage(configuration.percentage());
    }

    @Override
    public Money calculateDiscountedPrice(Money totalPrice, Integer quantity) {
        Money toSubtract = totalPrice.percentage(configuration.percentage());
        return totalPrice.subtract(toSubtract);
    }
}

record PercentageDiscountProperties(Integer percentage) implements DiscountProperties {}
