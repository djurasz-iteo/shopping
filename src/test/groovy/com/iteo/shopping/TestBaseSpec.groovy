package com.iteo.shopping

import com.iteo.shopping.discount.DiscountController
import com.iteo.shopping.discount.DiscountRepository
import com.iteo.shopping.order.OrderController
import com.iteo.shopping.products.ProductController
import com.iteo.shopping.products.ProductFacade
import com.iteo.shopping.products.ProductRepository
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc
import jakarta.transaction.Transactional
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification


@SpringBootTest
@ContextConfiguration
@Testcontainers
@ActiveProfiles("test")
class TestBaseSpec extends Specification {

    @Autowired
    List<JpaRepository<?, ?>> repositories;
    @Autowired
    ProductRepository productRepository
    @Autowired
    DiscountRepository discountRepository

    @Autowired DiscountController discountController
    @Autowired ProductController productController
    @Autowired OrderController orderController

    TestHelper testHelper = new TestHelper()

    def setup() {
        RestAssuredMockMvc.standaloneSetup(productController, discountController, orderController)
    }

    def cleanup() {
        repositories.forEach {it.deleteAllInBatch()}
    }


}
