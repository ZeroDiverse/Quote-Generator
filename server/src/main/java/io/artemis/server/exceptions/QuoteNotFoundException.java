package io.artemis.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Quote Not Found")
public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException() {
        super();
    }
    public QuoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public QuoteNotFoundException(String message) {
        super(message);
    }
    public QuoteNotFoundException(Throwable cause) {
        super(cause);
    }
}