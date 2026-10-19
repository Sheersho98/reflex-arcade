package com.reflexarcade.api.player;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo){
        this.repo = repo;
    }

    @Transactional
    public PlayerResponse create(CreatePlayerRequest req){
        if(repo.existsByUsername(req.username())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists.");
        }
        return PlayerResponse.from(repo.save(new Player(req.username())));
    }

    @Transactional(readOnly = true)
    public PlayerResponse get(Long id){
        return repo.findById(id).map(PlayerResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<PlayerResponse> list(){
        return repo.findAll().stream().map(PlayerResponse::from).toList();
    }

    @Transactional
    public void delete(Long id){
        if(!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repo.deleteById(id);
    }
}
