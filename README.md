# How to run

`cd gourmetdiscovery && docker compose build --no-cache && docker compose up -d && cd ../tastegateway && docker compose build --no-cache && docker compose up -d && cd ../infra && docker compose up -d && cd ../authub && docker compose -f docker-compose.dev.yml build --no-cache && docker compose -f docker-compose.dev.yml up -d
`
