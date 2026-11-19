package com.reflexarcade.api.session;

import com.reflexarcade.api.player.Player;
import com.reflexarcade.api.player.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GameSessionService {
    private final GameSessionRepository repo;
    private final PlayerRepository playerRepo;

    public GameSessionService(GameSessionRepository repo, PlayerRepository playerRepo){
        this.repo = repo;
        this.playerRepo = playerRepo;
    }

    @Transactional
    public SessionResponse create(CreateSessionRequest req){
        Player p = playerRepo.findById(req.playerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return SessionResponse.from(repo.save(new GameSession(p, req.mode())));
    }

    @Transactional(readOnly = true)
    public SessionResponse get(Long id){
        return repo.findById(id).map(SessionResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<SessionResponse> getSessionByPlayerId(Long playerId){
        if(!playerRepo.existsById(playerId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return repo.findByPlayer_Id(playerId).stream().map(SessionResponse::from).toList();
    }
}
