package com.iteo.shopping.order;

import com.iteo.shopping.discount.Discount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class DiscountEvent implements OrderEvent {

    private final List<Discount> discounts;
    private boolean isProcessed = false;

    @Override
    public Boolean isProcessed() {
        return isProcessed;
    }

    @Override
    public void processed() {
        this.isProcessed = true;
    }
}
