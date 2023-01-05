package com.iteo.shopping.discount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Entity(name = "discounts")
@Getter
class DiscountEntity {
    @Id
    @Column(name = "id")
    public UUID id;

    @Column(name = "discount_descriptor")
    public String discountDescriptor;
    @Column(name = "discount_properties")
    public String properties;

    public static DiscountEntity create(String discountDescriptor, String properties) {
        DiscountProperties details = DiscountFactory.createDetails(discountDescriptor, properties);

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.id = UUID.randomUUID();
        discountEntity.discountDescriptor = discountDescriptor;
        discountEntity.properties = details.asJsonString();
        return discountEntity;
    }

}
