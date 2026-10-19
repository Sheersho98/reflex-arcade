package com.reflexarcade.api.player;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePlayerRequest (@NotBlank @Size(max=30)String username){
}
