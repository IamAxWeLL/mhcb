package com.mhcb.domain;

public enum UserRole {
    ADMIN, USER;

    private String userRole;

    UserRole() {
    }

    UserRole(final String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}

