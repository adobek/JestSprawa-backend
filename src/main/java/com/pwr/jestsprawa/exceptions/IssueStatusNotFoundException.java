package com.pwr.jestsprawa.exceptions;

public class IssueStatusNotFoundException extends RuntimeException {
    public IssueStatusNotFoundException() { super("Status of issue not found"); }
}
