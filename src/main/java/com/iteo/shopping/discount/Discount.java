package com.iteo.shopping.discount;

import com.iteo.shopping.shared.Money;

import java.util.UUID;

public interface Discount {

    UUID id();
    Money calculateDiscountedPrice(Money price);

    Money calculateDiscountedPrice(Money price, Integer quantity);
}
