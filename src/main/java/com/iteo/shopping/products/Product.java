package com.iteo.shopping.products;

import com.iteo.shopping.shared.Money;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "products")
class Product {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "base_price"))
    private Money basePrice;


    public static Product createEmptyProduct(String name, BigDecimal price) {
        Product product = new Product();
        product.id = UUID.randomUUID();
        product.name = name;
        product.basePrice = new Money(price);
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
