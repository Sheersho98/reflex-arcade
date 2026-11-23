package com.reflexarcade.api.score;

import com.reflexarcade.api.session.GameSession;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch =  FetchType.LAZY, optional = false) @JoinColumn(name="session_id") private GameSession session;

    @Column(nullable = false)
    private int scoreMs;

    @Column(name="submitted_at", nullable = false, updatable = false)
    private Instant submittedAt = Instant.now();

    protected Score(){}

    public Score(GameSession session, int scoreMs) {
        this.session = session;
        this.scoreMs = scoreMs;
    }

    public Long getId() {
        return id;
    }

    public GameSession getSession(){
        return session;
    }

    public int getScoreMs() {
        return scoreMs;
    }

    public Instant getSubmittedAt() {
        return submittedAt;
    }
}
