DROP TABLE IF EXISTS laboratories;

CREATE TABLE laboratories
(
    id        varchar(32) primary key,
    name      varchar(100) not null,
    address   varchar(100) not null,
    activated boolean      not null,
    version   int          not null
);