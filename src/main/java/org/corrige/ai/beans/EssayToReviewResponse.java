package org.corrige.ai.beans;

import org.corrige.ai.models.Essay;

public class EssayToReviewResponse {
    private String reviewId;
    private Essay essay;

    public EssayToReviewResponse() {}

    public EssayToReviewResponse(String reviewId, Essay essay) {
        this.reviewId = reviewId;
        this.essay = essay;
    }

    public String getReviewId() {
        return this.reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Essay getEssay() {
        return this.essay;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }
    
}