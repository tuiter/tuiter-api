package org.corrige.ai.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
	IMAGE("Image"),
    TEXT("Text");

    private final String type;

    private Type(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return this.type;
    }

}
