package com.demo.payload;

import java.time.LocalDate;

public class ErrorDetails {

    private LocalDate date;
    private String errorMessage;
    private String request;

    public ErrorDetails(String request) {
        this.request = request;
    }

    public ErrorDetails(LocalDate now, String message, String description) {
        this.date = now;
        this.errorMessage = message;
        this.request = description;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
