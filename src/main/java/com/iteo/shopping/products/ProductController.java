package com.iteo.shopping.products;

import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(productFacade.createAProduct(dto));
    }

//    @PutMapping("/{id}/discounts/{discountId}")
//    public ResponseEntity<ProductResponse> activateDiscount(@PathVariable("id") String productId, @PathVariable("discountId") String discountId) {
//        var saved = productRepository.findById(UUID.fromString(productId))
//                .map(product -> product.activateDiscount(discountId))
//                .map(productRepository::save)
//                .orElseThrow(() -> new EntityNotFoundException("Product with id %s doesn't exists".formatted(productId)));
//        return ResponseEntity.ok(mapToDto(saved));
//    }

    @GetMapping
    public ResponseEntity<List<ProductView>> getProducts() {
        return ResponseEntity.ok(productFacade.findAllProducts());
    }

//    @GetMapping("/{id}/price")
//    public ResponseEntity<Price> calculatePrice (@PathVariable("id") String productId, @RequestParam Integer quantity) {
//        var product = productRepository.findById(UUID.fromString(productId)).orElseThrow(() -> new EntityNotFoundException("Product with id %s doesn't exists".formatted(productId)));
//        Price expectedCost = discountFacade.previewCostAfterDiscount(new Price(product.getBasePrice()), quantity, product.getActiveDiscountId().toString());
//        return ResponseEntity.ok(expectedCost);
//    }


}





