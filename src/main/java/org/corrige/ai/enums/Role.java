package org.corrige.ai.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	FREE_STUDENT("Free"), PREMIUM_STUDENT("Premium"), TEACHER("Teacher");
	
	private final String name;
	
	private Role(String name) {
		this.name = name;
	}
	
    @JsonValue
    public String getName() {
        return this.name;
    }

}
