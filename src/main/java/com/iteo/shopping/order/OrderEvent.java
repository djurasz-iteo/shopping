package com.iteo.shopping.order;


public interface OrderEvent {
    Boolean isProcessed();
    void processed();
}
