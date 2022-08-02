package org.learning.spring.security;

public enum Role {
    MANAGER("manager"),
    USER("user");

    private String value;
    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Role{" +
            "value='" + value + '\'' +
            '}';
    }
}
