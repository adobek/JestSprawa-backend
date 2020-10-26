package com.pwr.jestsprawa.exceptions;

public class IssueNotFoundException extends RuntimeException{
    public IssueNotFoundException(){
        super("Issue not found");
    }
}
