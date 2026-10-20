package com.reflexarcade.api.session;

import com.reflexarcade.api.player.Player;
import jakarta.persistence.*;

import javax.management.relation.RelationType;
import java.time.Instant;

@Entity
@Table(name="game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY, optional = false) @JoinColumn(name="player_id") private Player player;

    @Column(nullable = false, length = 30)
    private String mode;

    @Column(name="started_at", nullable = false, updatable = false)
    private Instant startedAt = Instant.now();

    protected GameSession(){}

    public GameSession(Player player, String mode){
        this.player = player;
        this.mode = mode;
    }

    public Long getId() {
        return id;
    }

    public Long getPlayerId() {
        return player.getId();
    }

    public String getMode() {
        return mode;
    }

    public Instant getStartedAt() {
        return startedAt;
    }
}
//TODO: One question to answer before you rely on it: player is LAZY,
// so on a GET /api/sessions/{id} Hibernate hands you a placeholder
// rather than a loaded Player. Does calling getId() on that placeholder
// need a database hit? Check what you find in the Hibernate docs on proxies
// and identifiers, or test it by watching the SQL logs. Add spring.jpa.show-sql=true
// to application.properties for that.