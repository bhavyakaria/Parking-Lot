package com.bhavyakaria.parking_lot.exceptions;

public class BaseException extends Exception {

    public BaseException() {
    }

    public BaseException(String message, int code) {
        System.out.printf("Message: %s\nStatus Code:%d", message, code);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
