package ru.pvorobey.checkrunnerrestful.exeptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;


@ControllerAdvice
public class ProductExceptionHandler {


    @ExceptionHandler(IdTypeProductNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(IdTypeProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(
                "message", exception.getMessage()));
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleException(NumberFormatException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(
                "message", exception.getMessage()+" - invalid data format"));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(
                "message", exception.getMessage()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleException(ConstraintViolationException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(
                "message", exception.getMessage()+" must not be null"));
    }
}
