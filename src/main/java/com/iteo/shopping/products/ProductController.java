package com.iteo.shopping.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/products")
class ProductController {

    private final ProductFacade productFacade;

    @GetMapping("/{id}")
    public ResponseEntity<ProductView> getProduct(@PathVariable("id") String productId) {
        return ResponseEntity.ok(productFacade.findAProduct(UUID.fromString(productId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
        productFacade.removeAProduct(UUID.fromString(productId));
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductView> createProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(productFacade.createAProduct(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductView>> getProducts() {
        return ResponseEntity.ok(productFacade.findAllProducts());
    }

}





