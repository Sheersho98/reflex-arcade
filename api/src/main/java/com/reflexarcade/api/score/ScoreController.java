package com.reflexarcade.api.score;

import com.reflexarcade.api.session.SessionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {
    private final ScoreService service;

    public ScoreController(ScoreService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScoreResponse create(@Valid @RequestBody CreateScoreRequest req){
        return service.create(req);
    }

    @GetMapping("/{id}")
    public ScoreResponse get(@PathVariable Long id){
        return service.get(id);
    }
}
