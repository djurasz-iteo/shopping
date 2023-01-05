package com.iteo.shopping.discount;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/discount")
class DiscountController {

    private final DiscountFacade discountFacade;
    @PostMapping
    ResponseEntity<DiscountView> createADiscount(@RequestBody DiscountDto dto) {
        return ResponseEntity.status(201).body(discountFacade.createADiscount(dto));
    }
}
