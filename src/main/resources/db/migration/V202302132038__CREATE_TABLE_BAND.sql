CREATE TABLE band
(
    uuid          varchar(36) primary key not null,
    name          varchar(100)            not null,
    description   varchar(500)            not null,
    active        BOOLEAN                 not null,
    owner_uuid    varchar(36)             not null,
    creation_date timestamp               not null,
    update_date   timestamp
);