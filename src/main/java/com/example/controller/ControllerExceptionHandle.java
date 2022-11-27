package com.example.controller;

import com.example.exception.CreationException;
import com.example.exception.ItemnotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler({ItemnotFoundException.class, CreationException.class})

    public ResponseEntity<?>handle(RuntimeException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
}
