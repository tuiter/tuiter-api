package org.corrige.ai.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PackageType {
	BASIC("Basic"), PLATINUM("Platinum");
	
	private final String name;
	
	private PackageType(String name) {
		this.name = name;
	}
	
    @JsonValue
    public String getName() {
        return this.name;
    }
}
