package com.reflexarcade.api.player;

import java.time.Instant;

public record PlayerResponse(Long id, String username, Instant createdAt) {
    static PlayerResponse from(Player p){
        return new PlayerResponse(p.getId(), p.getUsername(), p.getCreatedAt());
    }
}
