package com.mhcb.exception;

public class FormStateRequestException extends RuntimeException{

    public FormStateRequestException(final String message) {
        super(message);
    }

    public FormStateRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
