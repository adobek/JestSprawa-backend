package com.pwr.jestsprawa.exceptions;

public class DepartmentCategoryNotFoundException extends RuntimeException {
    public DepartmentCategoryNotFoundException() { super("Department and category link not found"); }
}
