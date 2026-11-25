## Folder Sturcture

reflex-arcade/
├── api/            # Spring Boot
├── web/            # Next.js
├── infra/          # docker-compose, k8s, grafana
├── docs/           # spec, ADRs, ai-workflow
├── AGENTS.md
├── NOTES.md
└── README.md


## Data model (implemented)
- players(id, username UNIQUE, created_at)
- game_sessions(id, player_id FK, mode, started_at)
- scores(id, session_id FK UNIQUE, score_ms CHECK >= 100, submitted_at)
- events(id, session_id FK, type, payload JSONB, created_at)

## Endpoints (implemented)
- POST/GET /api/players, GET/DELETE /api/players/{id}, GET /api/players/{id}/sessions
- POST /api/sessions, GET /api/sessions/{id}
- POST /api/scores, GET /api/scores/{id}
- POST /api/events, GET /api/events/{id}

## Session lifecycle contract
1. Client creates a session.
2. Client sends events during play (including `game_over`).
3. Client submits the score last. The score closes the session.
4. Events for a session that has a score are rejected (409).

## Business rules
- Score must be >= 100 ms (service rule, backed by a DB CHECK).
- One score per session (DB UNIQUE, plus a service check for a clean 409).
- Event payload is required but may be `{}`. `type` is the source of truth for event kind.

## Known limitations / decisions
- Event-after-score race is accepted (no cross-table constraint). Fix options: row lock or `ended_at` on sessions.
- Player deletion with existing sessions currently fails (to be mapped to 409).
- Anti-cheat is limited to the 100 ms floor.

## Not doing
Multiplayer, real-time sockets, payments, social features.