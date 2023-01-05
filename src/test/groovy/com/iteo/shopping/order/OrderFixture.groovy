package com.iteo.shopping.order

import org.apache.commons.lang.text.StrBuilder

class OrderFixture {

    static String requestForCostPreview(String productId, String ... discountIds) {
        StringBuilder discounts = new StringBuilder()
        for (final def discountId in discountIds) {
            discounts.append("\"$discountId\"")
        }
        return """{ "productId": "$productId", "quantity": 10, "discountIds": [$discounts] }"""
    }
}
