CREATE TABLE events(
    id              BIGSERIAL       PRIMARY KEY,
    session_id      BIGINT          NOT NULL        REFERENCES game_sessions(id),
    type            varchar(30)     NOT NULL,
    payload         JSONB           NOT NULL,
    created_at      TIMESTAMPTZ     NOT NULL        DEFAULT now()
);
CREATE INDEX idx_events_session_id ON events(session_id);