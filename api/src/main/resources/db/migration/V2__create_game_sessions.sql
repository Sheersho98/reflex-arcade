CREATE TABLE game_sessions(
    id          BIGSERIAL       PRIMARY KEY,
    player_id   BIGINT          REFERENCES players(id),
    mode        VARCHAR(30)     NOT NULL,
    started_at  TIMESTAMPTZ     NOT NULL DEFAULT now()
);
CREATE INDEX idx_game_sessions_player_id ON game_sessions(player_id);