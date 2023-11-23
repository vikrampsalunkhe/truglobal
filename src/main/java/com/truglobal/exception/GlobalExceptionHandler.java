package com.truglobal.exception;

import com.truglobal.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ResponseDto> handleException(Exception e, Locale locale) {
        ResponseDto response = ResponseDto.builder().statusCode(500L).message(e.getMessage()).build();
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ResponseDto> handleAxisException(CustomException e, Locale locale) {
        ResponseDto response = ResponseDto.builder().statusCode(400).message(e.getErrorMessage()).build();
        return ResponseEntity.status(400).body(response);
    }

}
