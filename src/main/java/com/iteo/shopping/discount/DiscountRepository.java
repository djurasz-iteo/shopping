package com.iteo.shopping.discount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<DiscountEntity, UUID> {
}
