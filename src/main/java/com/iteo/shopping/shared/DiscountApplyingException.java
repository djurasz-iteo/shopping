package com.iteo.shopping.shared;

public class DiscountApplyingException extends RuntimeException {
    private final String message;

    public DiscountApplyingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
