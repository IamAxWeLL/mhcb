package com.mhcb.exception;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class FormStateException  {
    private final String message;
    private final ZonedDateTime timestamp;
}
