# Reflex Arcade — agent instructions
Stack: Spring Boot (Java, Maven) in /api, Next.js + TypeScript in /web, infra in /infra.
Rules:
- Run `./mvnw test` (api) / `npm test` (web) before declaring any task done.
- Small, reviewable diffs. No unrelated refactors.
- DB changes only via Flyway migrations in api/src/main/resources/db/migration.
- Never commit secrets; config via environment variables.
- Prefer constructor injection, records for DTOs, validation on all inputs.
- Ask before adding a new dependency.
Modes: the owner hand-writes core learning code (services, queries, React components);
agents handle boilerplate, tests, CI, docs, and review.