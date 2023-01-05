package com.iteo.shopping.order

import com.iteo.shopping.shared.Money
import spock.lang.Specification

import static com.iteo.shopping.discount.DiscountFixture.quantityPlus3_50Percent_AND_quantityPlus5_70PercentDiscount
import static com.iteo.shopping.discount.DiscountFixture.quantityPlus3_70PercentDiscount
import static com.iteo.shopping.discount.DiscountFixture.percent50Discount
import static com.iteo.shopping.discount.DiscountFixture.percent100Discount
import static com.iteo.shopping.discount.DiscountFixture.percent12Discount
import static com.iteo.shopping.products.ProductFixture.BASE_PRICE
import static com.iteo.shopping.products.ProductFixture.someProduct

class OrderSpec extends Specification {

    def "should not apply more then one discount"() {
        given:
            def productId = UUID.randomUUID()
            def discountId = UUID.randomUUID()
            def aProduct = someProduct(productId)
            def aDiscount = percent50Discount(discountId)
            def anotherDiscount = percent12Discount(discountId)
            def severalDiscounts = [aDiscount, anotherDiscount]

        when:
            def order = Order.preview(aProduct)
            order.acceptEvent(new DiscountEvent(severalDiscounts))
            order.processDiscountEvent()

        then:
            order.events.size() == 0
            order.totalPrice == BASE_PRICE
    }

    def "should not accept next discount when previous one was processed"() {
        given:
        def productId = UUID.randomUUID()
        def discountId = UUID.randomUUID()
        def aProduct = someProduct(productId)
        def aDiscount = percent50Discount(discountId)
        def anotherDiscount = percent12Discount(discountId)

        when:
        def order = Order.preview(aProduct)
        order.acceptEvent(new DiscountEvent(List.of(aDiscount)))
        order.processDiscountEvent()

        then:
        order.events.size() == 1
        order.totalPrice == new Money(BigDecimal.valueOf(5))

        when:
        order.acceptEvent(new DiscountEvent(List.of(anotherDiscount)))
        order.processDiscountEvent()

        then:
        order.events.size() == 1
        order.totalPrice == new Money(BigDecimal.valueOf(5))
    }

    def "Should apply a 50% discount on item"() {
        given:
            def productId = UUID.randomUUID()
            def discountId = UUID.randomUUID()
            def aProduct = someProduct(productId)
            def aDiscount = percent50Discount(discountId)

        when:
            def order = Order.preview(aProduct)
            order.acceptEvent(new DiscountEvent(List.of(aDiscount)))
            order.processDiscountEvent()

        then:
        order.totalPrice == new Money(BigDecimal.valueOf(5))
    }

    def "Should apply a 12% discount on item"() {
        given:
        def productId = UUID.randomUUID()
        def discountId = UUID.randomUUID()
        def aProduct = someProduct(productId)
        def aDiscount = percent12Discount(discountId)

        when:
        def order = Order.preview(aProduct)
        order.acceptEvent(new DiscountEvent(List.of(aDiscount)))
        order.processDiscountEvent()

        then:
        order.totalPrice == new Money(BigDecimal.valueOf(9))
    }

    def "Should apply a 100% discount on item"() {
        given:
        def productId = UUID.randomUUID()
        def discountId = UUID.randomUUID()
        def aProduct = someProduct(productId)
        def aDiscount = percent100Discount(discountId)

        when:
        def order = Order.preview(aProduct)
        order.acceptEvent(new DiscountEvent(List.of(aDiscount)))
        order.processDiscountEvent()

        then:
        order.totalPrice == new Money(BigDecimal.ZERO)
    }

    def "Should apply quantity discount when there are more then 3 products"() {
        given:
        def productId = UUID.randomUUID()
        def discountId = UUID.randomUUID()
        def productQuantity = 3
        def aProduct = someProduct(productId, BASE_PRICE, productQuantity)
        def aDiscount = quantityPlus3_70PercentDiscount(discountId)

        when:
        def order = Order.preview(aProduct)

        then:
        order.events.size() == 0
        order.totalPrice == new Money(BigDecimal.valueOf(30))

        when:
        order.acceptEvent(new DiscountEvent(List.of(aDiscount)))
        order.processDiscountEvent()

        then:
        order.totalPrice == new Money(BigDecimal.valueOf(9))
    }

    def "Should apply better quantity discount when there are more then 5 products"() {
        given:
        def productId = UUID.randomUUID()
        def discountId = UUID.randomUUID()
        def productQuantity = 5
        def aProduct = someProduct(productId, BASE_PRICE, productQuantity)
        def aDiscount = quantityPlus3_50Percent_AND_quantityPlus5_70PercentDiscount(discountId)

        when:
        def order = Order.preview(aProduct)

        then:
        order.totalPrice == new Money(BigDecimal.valueOf(50))

        when:
        order.acceptEvent(new DiscountEvent(List.of(aDiscount)))
        order.processDiscountEvent()

        then:
        order.totalPrice == new Money(BigDecimal.valueOf(15))
    }



}
