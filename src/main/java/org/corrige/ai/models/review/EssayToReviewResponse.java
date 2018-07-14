package org.corrige.ai.models.review;

import org.corrige.ai.models.essay.Essay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EssayToReviewResponse {
    private String reviewId;
    private Essay essay;
}