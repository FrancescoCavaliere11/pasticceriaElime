package org.elime.elimebackend.security.exception.customException;

public class MissingEntityIdException extends RuntimeException{
    private final String message;

    public MissingEntityIdException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
