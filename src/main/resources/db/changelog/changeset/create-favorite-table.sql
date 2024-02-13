-- changeSet:create-favorite-table
CREATE TABLE favorite
(
    user_id  BIGINT    NOT NULL,
    movie_id BIGINT    NOT NULL,
    date     TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, movie_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE
);