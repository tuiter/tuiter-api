package org.tuiter.util;

public enum Gender {
    M("Male"),
    F("Female"),
    UNKNOWN("Unknown");

    private final String name;

    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}