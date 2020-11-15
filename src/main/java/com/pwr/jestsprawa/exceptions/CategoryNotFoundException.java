package com.pwr.jestsprawa.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() { super("Category not found"); }
}
