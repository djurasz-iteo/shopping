package com.iteo.shopping.discount;

enum DiscountType {
    PERCENTAGE("PERCENTAGE"),
    QUANTITY("QUANTITY");

    private final String discountDescriptor;

    DiscountType(String discountDescriptor) {
        this.discountDescriptor = discountDescriptor;
    }

    public static DiscountType fromString(String discountDescriptor) {
        for (DiscountType value : DiscountType.values()) {
            if (value.discountDescriptor.equals(discountDescriptor)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Discount Type %s doesn't exists".formatted(discountDescriptor));
    }
}
