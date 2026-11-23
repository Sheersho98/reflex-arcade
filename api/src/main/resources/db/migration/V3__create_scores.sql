CREATE TABLE scores(
    id              BIGSERIAL       PRIMARY KEY,
    session_id      BIGINT          NOT NULL        UNIQUE      REFERENCES  game_sessions(id),
    score_ms        INTEGER         NOT NULL        CHECK (score_ms >= 100),
    submitted_at    TIMESTAMPTZ     NOT NULL        DEFAULT     now()
);