package com.iteo.shopping.order

import com.iteo.shopping.TestBaseSpec
import com.jayway.jsonpath.JsonPath
import com.jayway.restassured.path.json.config.JsonPathConfig
import org.springframework.boot.test.context.SpringBootTest

import static com.iteo.shopping.discount.DiscountFixture.requestPercentageDiscount
import static com.iteo.shopping.order.OrderFixture.requestForCostPreview
import static com.iteo.shopping.products.ProductFixture.requestProduct

class OrderITSpec extends TestBaseSpec {

    def "user should be able to create a simple product"() {
        given:
            def productRequest = requestProduct()
            def discountRequest = requestPercentageDiscount(50)

        when: 'user create a product'
            def productResponse = testHelper.post("/v1/products", productRequest)

        then: 'operation should succeed'
            productResponse.statusCode() == 200
            def productId = JsonPath.parse(productResponse.body.asString()).read("\$['uuid']")

        when: 'user create a discount'
            def discountResponse = testHelper.post("/v1/discounts", discountRequest)

        then: 'operation should succeed'
            discountResponse.statusCode() == 201
            def discountId = JsonPath.parse(discountResponse.body.asString()).read("\$['discountId']")
            def calculateCostRequest = requestForCostPreview(productId, discountId)

        when: 'user want to preview the cost of the product for 3 items'
            def calculationResponse = testHelper.post("/v1/order/preview", calculateCostRequest)

        //TODO: validate a good value
        then: 'user should get a price 5$'
            calculationResponse.statusCode() == 200


    }

}
