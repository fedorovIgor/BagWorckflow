package com.example.bags.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ResponseError {
    private HttpStatus status;
    private String message;
    private LocalDateTime date = LocalDateTime.now();

    public ResponseError( String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }
}
