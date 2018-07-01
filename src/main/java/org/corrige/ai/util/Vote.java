package org.corrige.ai.util;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Vote {
	UPVOTE("Upvote"),
    DOWNVOTE("Downvote");

    private final String vote;

    private Vote(String vote) {
        this.vote = vote;
    }

    @JsonValue
    public String getVote() {
        return this.vote;
    }

}
