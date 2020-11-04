package com.pwr.jestsprawa.exceptions;

public class CommuneNotFoundException extends RuntimeException {
    public CommuneNotFoundException() { super("Commune not found"); }
}
