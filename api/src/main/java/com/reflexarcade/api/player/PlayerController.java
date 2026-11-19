package com.reflexarcade.api.player;

import com.reflexarcade.api.session.GameSessionService;
import com.reflexarcade.api.session.SessionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService service;
    private final GameSessionService gameSessionService;

    public PlayerController(PlayerService service, GameSessionService gameSessionService){
        this.service = service;
        this.gameSessionService = gameSessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponse create(@Valid @RequestBody CreatePlayerRequest req){
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PlayerResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping
    public List<PlayerResponse> list(){
        return service.list();
    }

    @GetMapping("/{id}/sessions")
    public List<SessionResponse> listSessionByPlayerId(@PathVariable Long id){
        return gameSessionService.getSessionByPlayerId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
