create table quote
(
    uuid          varchar(36) primary key not null,
    event_uuid    varchar(36)             not null,
    band_uuid     varchar(36)             not null,
    description   varchar(1000)           not null,
    status        varchar(25)             not null,

    band_response varchar(1000),

    creation_date timestamp               not null,
    update_date   timestamp,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);