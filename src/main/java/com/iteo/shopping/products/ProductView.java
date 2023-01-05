package com.iteo.shopping.products;

import java.util.UUID;

public record ProductView(UUID uuid, String name, Integer price) {
}
