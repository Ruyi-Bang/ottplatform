# ottplatform

## Local development

The existing local settings are preserved in `src/main/resources/application-local.properties`.
This file is ignored by Git and excluded from packaged JARs. Activate the local profile
when running from the project directory:

```sh
./gradlew bootRun --args='--spring.profiles.active=local'
```

The application requires the configured PostgreSQL database and valid API credentials.
The `PORT` environment variable overrides the default port, `8081`.

## Shared configuration

`src/main/resources/application.properties` contains shared defaults and masked placeholders.
For a new checkout, supply real values through these environment variables, or create
an ignored `application-local.properties` with your local overrides:

- `SECURITY_TOKEN_SECRET`
- `OPENAI_API_KEY`
- `TMDB_AUTH_TOKEN`
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

`MASKED` values are placeholders and must be replaced for the application to work.
Packaged JARs use the shared defaults and need the environment variables above.

## Swagger UI

Swagger UI and OpenAPI documentation are enabled by default in every profile:

- Swagger UI: http://localhost:8081/swagger-ui.html
- OpenAPI JSON: http://localhost:8081/v3/api-docs

Use your configured port if it differs from `8081`.
To disable documentation, set both `SPRINGDOC_SWAGGER_UI_ENABLED=false` and
`SPRINGDOC_API_DOCS_ENABLED=false`.
