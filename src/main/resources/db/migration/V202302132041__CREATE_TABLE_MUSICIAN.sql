create table musician
(
    uuid          varchar(36) primary key not null,
    first_name    varchar(75)             not null,
    last_name     varchar(150)            not null,
    age           int,
    cpf           varchar(14)             not null,
    email         varchar(100)            not null,
    band_uuid     varchar(36)             not null,
    creation_date timestamp               not null,
    update_date   timestamp,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);