CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name varchar(200) NOT NULL,
    username varchar(200) NOT NULL,
    password varchar(200) NOT NULL
);