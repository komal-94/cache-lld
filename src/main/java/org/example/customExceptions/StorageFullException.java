package org.example.customExceptions;

public class StorageFullException extends RuntimeException {
    public StorageFullException(String msg) {
        super(msg);
    }
}

