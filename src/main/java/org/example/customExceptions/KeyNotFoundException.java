package org.example.customExceptions;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String msg) {
        super(msg);
    }
}
