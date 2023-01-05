package com.iteo.shopping.shared;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DiscountExceptionControllerAdvice {
    @ResponseBody
    @ExceptionHandler(DiscountApplyingException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFound(DiscountApplyingException e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));
    }
}
