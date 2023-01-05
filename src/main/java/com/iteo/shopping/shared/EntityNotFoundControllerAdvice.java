package com.iteo.shopping.shared;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class EntityNotFoundControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.badRequest().build();
    }

}
