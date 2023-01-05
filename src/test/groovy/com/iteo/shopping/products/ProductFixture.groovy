package com.iteo.shopping.products

import com.iteo.shopping.order.ProductToBuy
import com.iteo.shopping.shared.Money

class ProductFixture {

    static def BASE_PRICE = new Money(BigDecimal.TEN)
    static def BASE_PRICE_DECIMAL = BASE_PRICE.toBigDecimal()
    static def BASE_QUANTITY = 1

    static ProductToBuy someProduct(UUID uuid, Money basePrice = BASE_PRICE, Integer baseQuantity = BASE_QUANTITY) {
        return new ProductToBuy(uuid, basePrice, baseQuantity)
    }

    static String requestProduct(String name = "givenProduct", BigDecimal price = BASE_PRICE_DECIMAL) {
        return """{ "name": "$name", "price": $price }""".stripIndent()
    }

}
