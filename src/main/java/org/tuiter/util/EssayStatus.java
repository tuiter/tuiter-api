package org.tuiter.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EssayStatus {
    PENDING("Pending"),
    CORRECTED("Corrected");

    private final String name;

    private EssayStatus(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}
