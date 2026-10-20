package com.reflexarcade.api.session;

import com.reflexarcade.api.player.Player;
import jakarta.validation.constraints.*;

public record CreateSessionRequest(@NotNull @Positive Long playerId, @NotBlank @Size(max=30) String mode) {
}