-- changeSet:create-user-table
CREATE TABLE app_user
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    username VARCHAR(255) UNIQUE,
    email    VARCHAR(255) UNIQUE
);