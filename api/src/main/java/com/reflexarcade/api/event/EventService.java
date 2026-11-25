package com.reflexarcade.api.event;


import com.reflexarcade.api.score.CreateScoreRequest;
import com.reflexarcade.api.score.ScoreRepository;
import com.reflexarcade.api.session.GameSession;
import com.reflexarcade.api.session.GameSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventService {
    private final EventRepository repo;
    private final GameSessionRepository sessionRepo;
    private final ScoreRepository scoreRepo;

    public EventService(EventRepository repo, GameSessionRepository sessionRepo, ScoreRepository scoreRepo){
        this.repo = repo;
        this.sessionRepo = sessionRepo;
        this.scoreRepo = scoreRepo;
    }

    @Transactional
    public EventResponse create(CreateEventRequest req){
        GameSession gs = sessionRepo.findById(req.sessionId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found."));
        if(scoreRepo.existsBySessionId(req.sessionId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Session is finished.");
        }
        return EventResponse.from(repo.save(new Event(gs, req.type(), req.payload())));
    }

    @Transactional(readOnly = true)
    public EventResponse get(Long id){
        return repo.findById(id).map(EventResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
