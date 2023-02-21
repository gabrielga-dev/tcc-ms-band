create table music
(
    uuid          varchar(36) primary key not null,
    band_uuid     varchar(36)             not null,
    name          varchar(45)             not null,
    observation   varchar(1000),
    creation_date timestamp               not null,
    update_date   timestamp,

    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);