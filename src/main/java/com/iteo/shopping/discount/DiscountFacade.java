package com.iteo.shopping.discount;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DiscountFacade {

    private final DiscountService discountService;
    public List<Discount> findAllDiscounts() {
        return discountService.findAllDiscounts();
    }

    public DiscountView createADiscount(DiscountDto dto) {
        return discountService.createADiscount(dto);
    }
}
