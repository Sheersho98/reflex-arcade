package com.reflexarcade.api.event;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService service;

    public EventController(EventService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse create(@Valid @RequestBody CreateEventRequest req){
        return service.create(req);
    }

    @GetMapping("/{id}")
    public EventResponse get(@PathVariable Long id){
        return service.get(id);
    }
}
