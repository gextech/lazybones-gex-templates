CREATE TABLE heroes (
    id VARCHAR(100),
    name VARCHAR(255) NOT NULL UNIQUE,
    age BIGINT,
    immortal  BOOLEAN DEFAULT TRUE,
    version BIGINT NOT NULL,
    PRIMARY KEY (id)
);