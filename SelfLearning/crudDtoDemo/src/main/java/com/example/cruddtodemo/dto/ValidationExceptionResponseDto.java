package com.example.cruddtodemo.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDto {
    private LocalDateTime dateTime;
    private int statusCode;
    private String message;
    private String error;
    private String path;
    private Map<String, String> feildErrors;

    public ValidationExceptionResponseDto(LocalDateTime dateTime, int statusCode, String message, String error, String path, Map<String, String> feildErrors) {

        this.dateTime = dateTime;
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
        this.path = path;
        this.feildErrors = feildErrors;
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

    public Map<String, String> getFeildErrors() {
        return feildErrors;
    }

    public void setFeildErrors(Map<String, String> feildErrors) {
        this.feildErrors = feildErrors;
    }
}
