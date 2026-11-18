package com.example.cruddtodemo.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {
    private LocalDateTime dateTime;
    private int statusCode;
    private String message;
    private String error;
    private String path;

    public ExceptionResponseDto(LocalDateTime dateTime, int statusCode, String message, String error, String path) {
        this.dateTime = dateTime;
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
        this.path = path;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
