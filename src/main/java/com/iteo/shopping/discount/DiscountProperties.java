package com.iteo.shopping.discount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

interface DiscountProperties {
    default String asJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Couldn't write value as a string");
        }
    }
}
