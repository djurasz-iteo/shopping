package com.iteo.shopping.products;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProductService {

    private final ProductRepository productRepository;

    public ProductView getAProduct(UUID productId) {
        return mapToDto(productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id %s doesn't exists".formatted(productId))));
    }

    public ProductView createAProduct(ProductDto dto) {
        var saved = productRepository.save(Product.createEmptyProduct(dto.name(), dto.price()));
        return mapToDto(saved);
    }

    public void removeAProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductView> findAll() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

    }

    private ProductView mapToDto(Product product) {
        return new ProductView(product.getId(), product.getName(), product.getBasePrice().toBigDecimal());
    }


}

