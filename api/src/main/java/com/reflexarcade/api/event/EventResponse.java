package com.reflexarcade.api.event;

import java.time.Instant;
import java.util.Map;

public record EventResponse(Long id, Long sessionId, String type, Map<String, Object> payload, Instant createdAt) {
    static EventResponse from(Event event){
        return new EventResponse(event.getId(), event.getSession().getId(), event.getType(), event.getPayload(), event.getCreatedAt());
    }
}
