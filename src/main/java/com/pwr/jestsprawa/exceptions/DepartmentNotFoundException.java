package com.pwr.jestsprawa.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() { super("Department not found"); }
}
