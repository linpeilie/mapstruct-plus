package io.github.linpeilie;

public class ConvertException extends RuntimeException {

    public ConvertException(final String message) {
        super(message);
    }

    public ConvertException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
