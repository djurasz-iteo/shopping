package com.iteo.shopping.products;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public ProductView findAProduct(UUID productId) {
        return productService.getAProduct(productId);
    }

    public ProductView createAProduct(ProductDto dto) {
        return productService.createAProduct(dto);
    }

    public void removeAProduct(UUID productId) {
        productService.removeAProduct(productId);
    }

    public List<ProductView> findAllProducts() {
        return productService.findAll();
    }

}
