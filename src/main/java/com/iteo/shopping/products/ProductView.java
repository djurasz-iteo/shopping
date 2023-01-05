package com.iteo.shopping.products;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductView(UUID uuid, String name, BigDecimal price) {
}
