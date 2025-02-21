package com.demo.exception;

import com.demo.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class HandleException {

//    @ExceptionHandler(ResourceNotFound.class)
//     public ResponseEntity<ErrorDetails> handleException(ResourceNotFound e, WebRequest request){
//
//        ErrorDetails errorDetails= new ErrorDetails(LocalDate.now(), e.getMessage(),request.getDescription(false));
//return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);

    //     }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalException(Exception e, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
