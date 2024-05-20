package com.qonsult.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHundler {
    @ExceptionHandler (Exception.class)
    public ResponseEntity<ErrorMessage> hundleException(Exception exception, WebRequest request){
        exception.printStackTrace();
        ErrorMessage errorMessage= new ErrorMessage();
        errorMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setTimestamp(new Date());
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
