package com.reflexarcade.api.session;

import java.time.Instant;

public record SessionResponse(Long id, Long playerId, String mode, Instant startedAt) {
    static SessionResponse from(GameSession gs){
        return new SessionResponse(gs.getId(), gs.getPlayerId(), gs.getMode(), gs.getStartedAt());
    }
}
