package com.pwr.jestsprawa.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException() { super("Email already in use"); }
}
