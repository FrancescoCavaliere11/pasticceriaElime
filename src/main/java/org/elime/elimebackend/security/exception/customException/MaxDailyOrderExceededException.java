package org.elime.elimebackend.security.exception.customException;

public class MaxDailyOrderExceededException extends RuntimeException {
    private final String message;

    public MaxDailyOrderExceededException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
