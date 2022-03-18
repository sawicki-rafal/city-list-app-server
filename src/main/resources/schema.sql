CREATE TABLE city
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(255)   NOT NULL,
    photo   VARCHAR(65535) NOT NULL,
    version BIGINT         NOT NULL DEFAULT 0
);