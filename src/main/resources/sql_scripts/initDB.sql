CREATE SCHEMA devj140;

CREATE TABLE devj140.users
(
    login    VARCHAR(16) PRIMARY KEY,
    password VARCHAR(16) NOT NULL
);

