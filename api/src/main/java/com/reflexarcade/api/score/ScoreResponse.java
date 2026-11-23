package com.reflexarcade.api.score;

import java.time.Instant;

public record ScoreResponse(Long id, Long sessionId, int scoreMs, Instant submittedAt) {
    static ScoreResponse from(Score score){
        return new ScoreResponse(score.getId(), score.getSession().getId(), score.getScoreMs(), score.getSubmittedAt());
    }
}
