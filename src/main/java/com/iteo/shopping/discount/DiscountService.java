package com.iteo.shopping.discount;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountView createADiscount(DiscountDto dto) {
        DiscountEntity discountEntity = DiscountEntity.create(dto.discountDescriptor(), dto.configuration());
        discountRepository.save(discountEntity);
        return new DiscountView(discountEntity.getId().toString());
    }

    public List<Discount> findAllDiscounts() {
        return discountRepository.findAll().stream()
                .map(this::mapFromEntity)
                .collect(Collectors.toList());
    }

    private Discount mapFromEntity(DiscountEntity entity) {
        return DiscountFactory.createDiscount(entity);
    }
}

record DiscountDto (String discountDescriptor, String configuration) {}

record DiscountView(String discountId){}
