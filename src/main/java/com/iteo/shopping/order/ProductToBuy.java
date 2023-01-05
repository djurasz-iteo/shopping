package com.iteo.shopping.order;

import com.iteo.shopping.shared.Money;
import lombok.Value;

import java.util.UUID;

@Value
public class ProductToBuy {
    UUID id;
    Money basePrice;
    Integer quantity;
}
