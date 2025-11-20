package com.digis01.MMateoProgramacionNCapas.exception;

import com.digis01.MMateoProgramacionNCapas.JPA.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Result> handleResourceNotFound(ResourceNotFoundException ex) {
        Result result = new Result();
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
//        result.ex = ex;
        result.status = 404;

        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Result> handleResourceAlreadyExist(ResourceAlreadyExistsException ex) {
        Result result = new Result();
        result.correct = false;
        result.errorMessage = ex.getLocalizedMessage();
        result.status = 409;

        return new ResponseEntity<>(result, HttpStatus.CONFLICT);
    }

}
