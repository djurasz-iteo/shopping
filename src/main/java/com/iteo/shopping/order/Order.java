package com.iteo.shopping.order;

import com.iteo.shopping.discount.Discount;
import com.iteo.shopping.shared.Money;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static com.iteo.shopping.order.OrderDiscountPolicies.allCurrentPolicies;

@Slf4j
class Order {
    private ProductToBuy product;
    private Money price;

    private Money initialPrice;

    private List<OrderEvent> events;

    private List<OrderDiscountPolicies> orderDiscountPolicies;


    private Order() {
    }

    public static Order preview(ProductToBuy product) {
        Order order = new Order();
        order.product = product;
        order.price = product.getBasePrice().multiply(product.getQuantity());
        order.initialPrice = new Money(order.price.toBigDecimal());
        order.events = new ArrayList<>();
        order.orderDiscountPolicies = allCurrentPolicies();
        return order;
    }


    public Optional<Rejection> acceptEvent(OrderEvent event) {
        log.info("Applying event {}", event.getClass());


        if (event instanceof DiscountEvent) {
            List<DiscountEvent> discountsEvents = events.stream()
                    .filter(orderEvent -> orderEvent instanceof DiscountEvent)
                    .map(orderEvent -> (DiscountEvent) orderEvent)
                    .toList();

            Optional<Rejection> rejection = orderDiscountPolicies.stream()
                    .map(policy -> policy.apply((DiscountEvent) event, discountsEvents))
                    .filter(Either::isLeft)
                    .map(Either::getLeft).findAny();

            if (rejection.isEmpty()) {
                this.events.add(event);
                return Optional.empty();
            }
            return rejection;
        }

        return Optional.of(Rejection.withReason("Unsupported event type"));
    }

    public void processDiscountEvent() {
        Stream<DiscountEvent> discountEvents = events.stream()
                .filter(orderEvent -> !orderEvent.isProcessed())
                .filter(orderEvent -> orderEvent instanceof DiscountEvent)
                .map(orderEvent -> (DiscountEvent) orderEvent);

        discountEvents.forEach(this::applyDiscount);
    }

    private void applyDiscount(DiscountEvent event) {
        List<Discount> discounts = event.getDiscounts();

        discounts.forEach(discount -> changeOrderPriceOnDiscount(discount.calculateDiscountedPrice(this.price, product.getQuantity())));

        event.processed();
    }

    private void changeOrderPriceOnDiscount(Money newPrice) {
        this.price = newPrice;
    }


    public Money getTotalPrice() {
        return this.price;
    }

    public Money getInitialPrice() {
        return this.initialPrice;
    }

    public UUID getProductId() {
        return this.product.getId();
    }
}
