package com.truglobal.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private long errorCode;

    private String errorMessage;

    public CustomException(long errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomException (");
        sb.append("errorCode = ").append(errorCode);
        sb.append("errorMessage = ").append(errorMessage);
        sb.append(")");
        return sb.toString();
    }

}
