Frontend (React) https://github.com/DHYASUDA/AI-MMA-analyzer-frontend
# MMA AI Assistant

A Spring Boot backend that combines live MMA data from [SportsData.io](https://sportsdata.io/) with an OpenAI-powered chat assistant. The assistant answers questions about upcoming events, fighter stats, and matchups, and uses RAG (Retrieval-Augmented Generation) over a local MMA knowledge base stored in PostgreSQL with pgvector.

## Features

- **Live MMA data** — UFC schedules, event details, fighter records, and stats via the SportsData.io API
- **AI chat** — OpenAI `gpt-4o-mini` with structured prompts for event cards, fighter comparisons, and predictions
- **RAG knowledge base** — MMA rules, striking, submissions, and related content embedded with `text-embedding-3-small` and stored in pgvector
- **User accounts** — Sign up, login, and profile endpoints backed by PostgreSQL
- **REST API** — CORS enabled for a frontend at `http://localhost:5173`

## Tech stack

| Layer | Technology |
|-------|------------|
| Runtime | Java 17, Spring Boot 4.0.6 |
| AI | Spring AI 2.0.0-M4, OpenAI (chat + embeddings) |
| Database | PostgreSQL + pgvector |
| Build | Gradle |

## Prerequisites

- **Java 17**
- **PostgreSQL** with the [pgvector](https://github.com/pgvector/pgvector) extension
- **OpenAI API key** — [platform.openai.com](https://platform.openai.com/)
- **SportsData.io MMA API key** — [sportsdata.io](https://sportsdata.io/)

## Environment variables

API keys are **not** hardcoded in source. Set these before running the app:

| Variable | Purpose |
|----------|---------|
| `OPENAI_API_KEY` | OpenAI chat and embedding requests |
| `SPORTSDATA_API_KEY` | SportsData.io MMA API authentication |

**Windows (PowerShell)**

```powershell
$env:OPENAI_API_KEY = "your-openai-key"
$env:SPORTSDATA_API_KEY = "your-sportsdata-key"
```

**macOS / Linux**

```bash
export OPENAI_API_KEY="your-openai-key"
export SPORTSDATA_API_KEY="your-sportsdata-key"
```

These are referenced in `src/main/resources/application.properties`:

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
sportsdata.mma.api-key=${SPORTSDATA_API_KEY}
```

## Database setup

The app expects PostgreSQL on `localhost:5500` with database `fightdb`. Update `spring.datasource.*` in `application.properties` if your setup differs.

1. Create the database and enable pgvector:

```sql
CREATE DATABASE fightdb;
\c fightdb
CREATE EXTENSION vector;
```

2. On first startup, Hibernate creates JPA tables and Spring AI initializes the vector store schema. The `KnowledgeBaseLoader` ingests text files from `src/main/resources/knowledge/` into the vector store automatically.

Default local credentials in `application.properties` are `postgres` / `postgres` — change these for any shared or production environment.

## Running the app

```bash
./gradlew bootRun
```

On Windows:

```powershell
.\gradlew.bat bootRun
```

The server starts on the default Spring Boot port (`8080`).

Run tests (uses in-memory H2 with placeholder keys):

```bash
./gradlew test
```

## API overview

### MMA data — `/api/mma`

| Method | Path | Description |
|--------|------|-------------|
| GET | `/upcomingEvents` | UFC 2026 schedule |
| GET | `/nextEventDetails` | Next upcoming event |
| GET | `/get2026Events?year=2026` | Event names for a year |
| GET | `/getFightDetails?eventName=...&year=2026` | Fights on a specific card |
| GET | `/getAllFighters` | All fighters (basic info) |
| GET | `/getFighterStats?fighterId=...` | Stats for one fighter |
| GET | `/ai/next-event` | Next event parsed into a structured `fightCard` |

### AI chat — `/ai`

| Method | Path | Description |
|--------|------|-------------|
| POST | `/chat` | MMA assistant chat (plain text body) |

### Users — `/api`

| Method | Path | Description |
|--------|------|-------------|
| POST | `/signup` | Create an account |
| POST | `/login` | Authenticate |
| POST | `/edit` | Edit user profile |

## Project structure

```
src/main/java/techskill/demo/
├── Controller/     # REST endpoints (MMA, chat, users)
├── Service/        # Business logic and AI orchestration
├── RAG/            # Knowledge base loader for vector store
├── config/         # SportsData RestClient, CORS
├── Entity/         # JPA entities
├── DTO/            # Request/response objects
└── Repositories/   # Spring Data JPA

src/main/resources/
├── application.properties
├── knowledge/      # RAG source documents (*.txt)
└── static/         # Basic HTML landing page
```

## How the AI layer works

1. **Chat flow** — User message is parsed for fighter names; live stats and upcoming event data are fetched from SportsData.io and injected into the system prompt.
2. **RAG** — A `QuestionAnswerAdvisor` retrieves relevant chunks from the pgvector store (MMA rules, techniques, etc.) to ground answers.
3. **Structured output** — `/api/mma/ai/next-event` uses Spring AI structured output to return a typed `fightCard` object.

## Frontend

CORS is configured for `http://localhost:5173`, so this backend is intended to pair with a separate frontend (e.g. Vite/React) on that port.

## Roadmap

- Structured outputs for more endpoints
- Conversation memory
- Advanced RAG
- Home chat UI improvements

## License

Add a license if you plan to open-source this project.
