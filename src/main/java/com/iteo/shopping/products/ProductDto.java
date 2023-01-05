package com.iteo.shopping.products;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

public record ProductDto(@NonNull String name, @Nullable BigDecimal price) {
}
