package org.morecup.jimmerddd.java.sample.domain.base;

public class DomainException extends RuntimeException {
    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message) {
        super(message);
    }
}