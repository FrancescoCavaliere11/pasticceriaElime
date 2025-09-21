package org.elime.elimebackend.security.exception.customException;

public class InvalidOrderDateException extends RuntimeException{
    private final String message;

    public InvalidOrderDateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
