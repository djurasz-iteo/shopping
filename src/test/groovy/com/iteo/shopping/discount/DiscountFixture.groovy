package com.iteo.shopping.discount


class DiscountFixture {

    static def DISCOUNT_ID = UUID.randomUUID()


    static Discount percent50Discount(UUID discountId = DISCOUNT_ID) {
        return new PercentageDiscount(discountId, new PercentageDiscountProperties(50))
    }

    static Discount percent12Discount(UUID discountId = DISCOUNT_ID) {
        return new PercentageDiscount(discountId, new PercentageDiscountProperties(12))
    }

    static Discount percent100Discount(UUID discountId = DISCOUNT_ID) {
        return new PercentageDiscount(discountId, new PercentageDiscountProperties(100))
    }

    static Discount quantityPlus3_70PercentDiscount(UUID discountId = DISCOUNT_ID) {
        return new QuantityDiscount(discountId, new QuantityDiscountProperties(Map.of(3, 70)))
    }

    static Discount quantityPlus3_50Percent_AND_quantityPlus5_70PercentDiscount(UUID discountId = DISCOUNT_ID) {
        return new QuantityDiscount(discountId, new QuantityDiscountProperties(Map.of(3, 50, 5, 70)))
    }

    static String requestPercentageDiscount(Integer percentage) {
        return """{ "discountDescriptor": "PERCENTAGE", "configuration": "{ \\"percentage\\": $percentage }" }"""
    }

}
