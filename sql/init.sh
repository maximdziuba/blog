set -e

export PGPASSWORD="$POSTGRES_PASSWORD"

psql -v --username "$POSTGRES_USER" --dbname "$POSTGRES_DB"
CREATE DATABASE blog;