package org.corrige.ai.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReviewStatus {
    PENDING("Pending"),
    CORRECTED("Corrected");

    private final String name;

    private ReviewStatus(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}
