package com.example.myapp;

import com.example.myapp.exception.InternalServerErrorException;
import com.example.myapp.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    Logger logger;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(HttpServletRequest request) {
        logger.warn("NotFoundException {}", request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(("Entity not found"));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<String> handleInternalServerErrorException(HttpServletRequest request, Exception e) {
        logger.warn("InternalServerErrorException {}", request.getRequestURI(), e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(("Internal Server Error"));
    }
}
