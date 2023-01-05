package com.iteo.shopping.order;

import com.iteo.shopping.discount.DiscountFacade;
import com.iteo.shopping.products.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class OrderConfiguration {

    @Bean
    public OrderManager orderManager(ProductFacade productFacade, DiscountFacade discountFacade) {
        return new OrderManager(productFacade, discountFacade);
    }

    @Bean
    public OrderFacade orderFacade(OrderManager orderManager) {
        return new OrderFacade(orderManager);
    }
}
