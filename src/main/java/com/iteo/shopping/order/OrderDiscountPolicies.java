package com.iteo.shopping.order;

import io.vavr.Function2;
import io.vavr.control.Either;
import lombok.NonNull;
import lombok.Value;

import java.util.List;


public interface OrderDiscountPolicies extends Function2<DiscountEvent, List<DiscountEvent>, Either<Rejection, Allowance>> {

    int MAX_DISCOUNT_NUMBER = 1;
    int MAX_ALREADY_APPLIED = 0;

    OrderDiscountPolicies onlySinglePolicyPerOrder = (DiscountEvent discountToApply, List<DiscountEvent> second) -> {
        if(discountToApply.getDiscounts().size() > MAX_DISCOUNT_NUMBER) {
            return Either.left(Rejection.withReason("You can't apply more than one discount"));
        }
        return Either.right(new Allowance());
    };

    OrderDiscountPolicies alreadyAppliedDiscounts = (DiscountEvent discountToApply, List<DiscountEvent> second) -> {
        if(second.size() > MAX_ALREADY_APPLIED) {
            return Either.left(Rejection.withReason("There is already applied a discount for the order"));
        }
        return Either.right(new Allowance());
    };

    static List<OrderDiscountPolicies> allCurrentPolicies() {
        return List.of(onlySinglePolicyPerOrder, alreadyAppliedDiscounts);
    }
}

@Value
class Allowance { }

@Value
class Rejection {

    @Value
    static class Reason {
        @NonNull
        String reason;
    }

    @NonNull
    Reason reason;

    static Rejection withReason(String reason) {
        return new Rejection(new Reason(reason));
    }
}
