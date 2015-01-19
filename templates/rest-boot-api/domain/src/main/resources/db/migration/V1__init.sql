CREATE TABLE person (
    id VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(512) NOT NULL,
    date_created timestamp,
    last_updated timestamp,
    version BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role (
    id VARCHAR(100),
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(512),
    active BOOLEAN DEFAULT true,
    date_created timestamp,
    last_updated timestamp,
    version BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE person_role (
    user_roles_id varchar(255) references person(id),
    role_id varchar(255) references role(id)
);

INSERT INTO person VALUES('test-id', 'test@test.com', 'test', '$2a$10$GOfR..HbTfi2ztSbZOcVXOHw0EYvbh2Que9N0vIy1AmBiEa8M9pYe', current_timestamp, current_timestamp, 0);

INSERT INTO role VALUES('admin-role', 'ROLE_ADMIN', 'Admins have this role', true, current_timestamp, current_timestamp, 0);

INSERT INTO person_role VALUES ('test-id', 'admin-role');

