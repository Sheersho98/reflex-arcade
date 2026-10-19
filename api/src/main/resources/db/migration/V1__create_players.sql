CREATE TABLE players(
    id              BIGSERIAL   PRIMARY KEY,
    username        VARCHAR(30) NOT NULL UNIQUE,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);