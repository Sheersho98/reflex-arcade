package com.reflexarcade.api.score;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateScoreRequest(@NotNull @Positive Long sessionId, @NotNull @Positive Integer scoreMs){
}
