package com.truglobal.response;

import com.truglobal.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class ResponseHandler {

    public static ResponseEntity<ResponseDto> generateResponse(String message, HttpStatus status, Object responseObj) {
        ResponseDto responseDto = ResponseDto.builder().statusCode(status.value()).message(message).data(responseObj)
                .build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.valueOf(status.value()));
    }

    public static ResponseEntity<ResponseDto> generateResponse(List<FieldError> errorMessages) {
        StringBuilder errors = new StringBuilder();
        for (FieldError error : errorMessages) {
            errors.append(error.getDefaultMessage());
            errors.append("\n");
        }
        ResponseDto responseDto = ResponseDto.builder().statusCode(HttpStatus.BAD_REQUEST.value())
                .message(errors.toString()).build();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.BAD_REQUEST);
    }
}