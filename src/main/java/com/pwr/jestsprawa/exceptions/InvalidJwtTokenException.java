package com.pwr.jestsprawa.exceptions;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException() { super("Invalid or expired token"); }
}
