package com.iteo.shopping.products;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record ProductDto(@NonNull String name, @Nullable Integer price) {
}
