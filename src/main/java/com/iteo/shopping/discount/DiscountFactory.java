package com.iteo.shopping.discount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class DiscountFactory {

    public static Discount createDiscount(DiscountEntity entity) {
        DiscountType type = DiscountType.fromString(entity.discountDescriptor);

        return switch (type) {
            case PERCENTAGE -> new PercentageDiscount(entity.id, readJson(entity.properties, PercentageDiscountProperties.class));
            case QUANTITY -> new QuantityDiscount(entity.id, readJson(entity.properties, QuantityDiscountProperties.class));
        };
    }

    public static DiscountProperties createDetails(String discountDescriptor, String properties) {
        DiscountType type = DiscountType.fromString(discountDescriptor);

        return switch (type) {
            case PERCENTAGE -> readJson(properties, PercentageDiscountProperties.class);
            case QUANTITY -> readJson(properties, QuantityDiscountProperties.class);
        };
    }

    private static <T> T readJson(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Couldn't read a discount details for class {}", clazz.getName());
            throw new IllegalArgumentException("Couldn't read a discount details");
        }
    }
}
