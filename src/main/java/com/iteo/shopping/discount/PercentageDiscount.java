package com.iteo.shopping.discount;

import com.iteo.shopping.shared.Money;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
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
        Money toSubtract = price.percentage(BigDecimal.valueOf(configuration.percentage()));
        return price.subtract(toSubtract);
    }

    @Override
    public Money calculateDiscountedPrice(Money totalPrice, Integer quantity) {
        Money toSubtract = totalPrice.percentage(BigDecimal.valueOf(configuration.percentage()));
        return totalPrice.subtract(toSubtract);
    }
}

record PercentageDiscountProperties(Integer percentage) implements DiscountProperties {}
