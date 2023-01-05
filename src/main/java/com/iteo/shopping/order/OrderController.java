package com.iteo.shopping.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/order")
class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping("/preview")
    public ResponseEntity<OrderSummary> previewAnOrder(@Validated @RequestBody OrderDto dto) {
        List<UUID> discountIds = dto.discountIds().stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderFacade.previewOrderWithDiscounts(UUID.fromString(dto.productId()),
                dto.quantity(),
                discountIds));
    }
}

record OrderDto(String productId, Integer quantity, List<String> discountIds) {
}
