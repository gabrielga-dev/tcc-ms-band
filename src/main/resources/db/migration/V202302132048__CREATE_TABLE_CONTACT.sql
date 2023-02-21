create table contact
(
    uuid          varchar(36) primary key not null,

    band_uuid     varchar(36)             not null,

    type          varchar(50)             not null,
    content       varchar(150)            not null,
    creation_date timestamp               not null,
    update_date   timestamp,

    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);