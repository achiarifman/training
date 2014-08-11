package com.vidmind.training.exception;

/**
 * Created by Achia.Rifman on 06/08/2014.
 */
public class ExceptionMessage {

    String message;

    public ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
