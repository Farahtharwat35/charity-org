package com.charity_org.demo.DTO;

public class ErrorResponseDTO {
    private String message;
    private int statusCode;

    // Constructor to initialize message and status code
    public ErrorResponseDTO(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    // Getter for status code
    public int getStatusCode() {
        return statusCode;
    }

    // Setter for status code
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
