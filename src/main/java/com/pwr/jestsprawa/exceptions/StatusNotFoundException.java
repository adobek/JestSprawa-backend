package com.pwr.jestsprawa.exceptions;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException() { super("Status not found"); }
}
