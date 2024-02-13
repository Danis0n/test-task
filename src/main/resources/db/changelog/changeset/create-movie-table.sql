-- changeSet:create-movie-table
CREATE TABLE movie
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(255) UNIQUE,
    poster_path VARCHAR(255)
);
