package com.iteo.shopping.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
class DiscountConfiguration {

    private final DiscountRepository discountRepository;
    @Bean
    public DiscountService discountService() {
        return new DiscountService(discountRepository);
    }
    @Bean
    public DiscountFacade discountFacade(DiscountService discountService) {
        return new DiscountFacade(discountService);
    }
}
