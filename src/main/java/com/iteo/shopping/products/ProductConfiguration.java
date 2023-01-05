package com.iteo.shopping.products;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class ProductConfiguration {
    private final ProductRepository productRepository;

    @Bean
    public ProductService productService() {
        return new ProductService(productRepository);
    }

    @Bean
    public ProductFacade productFacade(ProductService productService) {
        return new ProductFacade(productService);
    }
}
