CREATE TABLE IF NOT EXISTS post(
    id    BIGINT PRIMARY KEY,
    title VARCHAR(255),
    text  TEXT
);

DO $$
    DECLARE
        i integer := 0;
    BEGIN
        WHILE i < 100 LOOP
                PERFORM pg_sleep(0.01); -- delay to ensure random values
                INSERT INTO post (id, title, text)
                VALUES (i, concat('Post Title #', i), md5(random()::text));
                i := i + 1;
            END LOOP;
    END $$;
