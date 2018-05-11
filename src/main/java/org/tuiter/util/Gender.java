package org.tuiter.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    M("Male"),
    F("Female"),
    UNKNOWN("Unknown");

    private final String name;

    private Gender(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}