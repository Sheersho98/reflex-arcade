package com.reflexarcade.api.event;

import jakarta.validation.constraints.*;

import java.util.Map;

public record CreateEventRequest(@NotNull @Positive Long sessionId, @NotBlank @Size(max=30) String type, @NotNull
                                 Map<String, Object> payload) {
}
