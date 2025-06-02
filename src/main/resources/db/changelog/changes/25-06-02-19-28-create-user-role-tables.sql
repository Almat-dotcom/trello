-- liquidbase formatted sql
-- changeset Almat Sagandykov:1
-- date: 2025-06-02

CREATE TABLE users
(
    ID            bigserial primary key,
    FULL_NAME     varchar(255) not null,
    USERNAME      varchar(255) not null unique,
    PASSWORD      varchar(255) not null,
    AGE           int,
    CREATED_DATE  timestamp,
    MODIFIED_DATE timestamp
);

CREATE TABLE roles
(
    ID            bigserial primary key,
    NAME          varchar(255) not null unique,
    DESCRIPTION   text,
    CREATED_DATE  timestamp,
    MODIFIED_DATE timestamp
);

CREATE TABLE user_roles
(
    USER_ID       bigint not null references users(ID),
    ROLE_ID       bigint not null references roles(ID),
    primary key (USER_ID, ROLE_ID)
);