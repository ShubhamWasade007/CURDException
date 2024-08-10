package com.javacurd.springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<String> employeeAlreadyExistException(EmployeeAlreadyExistException ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchEmployeeException.class)
    public ResponseEntity<String> noSuchEmployeeException(NoSuchEmployeeException ex)
    {
        log.info("Not Found Exception got");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



}
