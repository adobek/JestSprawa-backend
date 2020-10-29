package com.pwr.jestsprawa.model;

public enum RoleType {
    ADMIN("admin"),
    EMPLOYEE("pracownik"),
    APPLICANT("zgłaszający");

    private final String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
