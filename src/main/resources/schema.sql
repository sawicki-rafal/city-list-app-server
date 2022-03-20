CREATE TABLE city
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(255)   NOT NULL,
    photo   VARCHAR(65535) NOT NULL,
    version BIGINT         NOT NULL DEFAULT 0
);

CREATE TABLE users
(
    username VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(64)  NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

create table authorities
(
    username  VARCHAR(255) NOT NULL,
    authority VARCHAR(50)  NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);