package com.iteo.shopping.order;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderFacade {

    private final OrderManager orderManager;
    public OrderSummary previewOrderWithDiscounts(UUID productId, Integer quantity, List<UUID> discountIds) {
        return orderManager.previewOrderWithDiscounts(productId, quantity, discountIds);
    }
}
