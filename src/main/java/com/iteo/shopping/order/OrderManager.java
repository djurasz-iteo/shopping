package com.iteo.shopping.order;

import com.iteo.shopping.discount.Discount;
import com.iteo.shopping.discount.DiscountFacade;
import com.iteo.shopping.products.ProductFacade;
import com.iteo.shopping.products.ProductView;
import com.iteo.shopping.shared.DiscountApplyingException;
import com.iteo.shopping.shared.Money;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
class OrderManager {

    private final ProductFacade productFacade;
    private final DiscountFacade discountFacade;

    public OrderSummary previewOrderWithDiscounts(UUID productId, Integer quantity, List<UUID> discountIds) {
        ProductView product = productFacade.findAProduct(productId);
        List<Discount> allDiscounts = discountFacade.findAllDiscounts();

        if (!hasValidDiscounts(discountIds, allDiscounts) || hasDuplicates(discountIds)) {
            throw new DiscountApplyingException("Discount ids you provided are invalid!");
        }

        List<Discount> discounts = allDiscounts.stream()
                .filter(discount -> discountIds.contains(discount.id()))
                .toList();

        ProductToBuy productToBuy = new ProductToBuy(productId, new Money(product.price()), quantity);

        Order order = Order.preview(productToBuy);

        applyDiscounts(order, discounts);

        return new OrderSummary(order.getProductId(), order.getTotalPrice().toString(), order.getInitialPrice().toString());

    }

    private boolean hasDuplicates(List<UUID> discountIds) {
        return Set.of(discountIds).size() != discountIds.size();
    }

    private boolean hasValidDiscounts(List<UUID> discountIds, List<Discount> allDiscounts) {
        return discountIds.stream().allMatch(uuid -> allDiscounts.stream().map(Discount::id).toList().contains(uuid));
    }

    private void applyDiscounts(Order order, List<Discount> discountChosen) {
        Optional<Rejection> rejection = order.acceptEvent(new DiscountEvent(discountChosen));

        if (noRejection(rejection)) {
            order.processDiscountEvent();
        } else {
            throw new DiscountApplyingException(rejection.get().getReason().getReason());
        }
    }

    private boolean noRejection(Optional<Rejection> rejection) {
        return rejection.isEmpty();
    }
}

record OrderSummary(UUID productId, String price, String initialPrice) {
}
