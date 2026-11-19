package com.reflexarcade.api.session;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    List<GameSession> findByPlayer_Id(Long playerId);
}
