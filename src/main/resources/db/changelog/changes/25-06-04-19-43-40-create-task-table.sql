create table tasks(
    ID bigserial primary key,
    TITLE varchar(255) not null,
    DESCRIPTION text,
    STATUS varchar(50) not null,
    USER_ID bigint references users(ID),
    CREATED_DATE  timestamp,
    MODIFIED_DATE timestamp
);