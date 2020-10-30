package com.pwr.jestsprawa.model;

public enum RoleType {
    ROLE_ADMIN("admin"),
    ROLE_EMPLOYEE("pracownik"),
    ROLE_APPLICANT("zgłaszający");

    private final String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RoleType fromString(String name) {
        for (RoleType roleType :
                RoleType.values()) {
            if (roleType.name.equalsIgnoreCase(name)) {
                return roleType;
            }
        }
        return null;
    }
}
