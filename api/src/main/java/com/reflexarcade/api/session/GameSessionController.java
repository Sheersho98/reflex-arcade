package com.reflexarcade.api.session;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class GameSessionController {
    private final GameSessionService service;

    public GameSessionController(GameSessionService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponse create(@Valid @RequestBody CreateSessionRequest req){
        return service.create(req);
    }

    @GetMapping("/{id}")
    public SessionResponse get(@PathVariable Long id){
        return service.get(id);
    }
}
