package com.reflexarcade.api.event;

import com.reflexarcade.api.session.GameSession;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name="session_id") private GameSession session;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> payload;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Event(){}

    public Event(GameSession session, String type, Map<String, Object> payload){
        this.session = session;
        this.type = type;
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public GameSession getSession() {
        return session;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
