package com.reflexarcade.api.score;

import com.reflexarcade.api.session.GameSession;
import com.reflexarcade.api.session.GameSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScoreService {
    private final ScoreRepository repo;
    private final GameSessionRepository sessionRepo;

    public ScoreService(ScoreRepository repo, GameSessionRepository sessionRepo){
        this.repo = repo;
        this.sessionRepo = sessionRepo;
    }

    @Transactional
    public ScoreResponse create(CreateScoreRequest req){
        if(req.scoreMs() < 100){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid score.");
        }
        GameSession session = sessionRepo.findById(req.sessionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found"));
        if(repo.existsBySessionId(req.sessionId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Score already exists.");
        }
        return ScoreResponse.from(repo.save(new Score(session, req.scoreMs())));
    }

    @Transactional(readOnly = true)
    public ScoreResponse get(Long id){
        return repo.findById(id).map(ScoreResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
