create table quote_request
(
    uuid                      varchar(36) primary key not null,
    event_uuid                varchar(36)             not null,
    origin_quote_request_uuid varchar(36)             not null,
    band_uuid                 varchar(36)             not null,
    description               varchar(1000)           not null,
    status                    varchar(15)             not null,
    creation_date             timestamp               not null,
    update_date               timestamp,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);

create table quote_request_music
(
    uuid               varchar(36) primary key not null,
    quote_request_uuid varchar(36)             not null,
    music_uuid         varchar(36)             not null,
    `order`            int,
    observation        varchar(500),
    FOREIGN KEY (quote_request_uuid) REFERENCES quote_request (uuid),
    FOREIGN KEY (music_uuid) REFERENCES music (uuid)
);

create table quote_request_musician_type
(
    uuid               varchar(36) primary key not null,
    quote_request_uuid varchar(36)             not null,
    musician_type_uuid varchar(36)             not null,
    quantity           int,
    observation        varchar(500),
    FOREIGN KEY (quote_request_uuid) REFERENCES quote_request (uuid),
    FOREIGN KEY (musician_type_uuid) REFERENCES musician_type (uuid)
);

#QUOTE
create table quote
(
    uuid               varchar(36) primary key not null,
    quote_request_uuid varchar(36)             not null,
    status             varchar(25)             not null,
    price              float                   not null,
    observation        varchar(500),
    FOREIGN KEY (quote_request_uuid) REFERENCES quote_request (uuid)
);

create table quote_musician
(
    quote_uuid    varchar(36) not null,
    musician_uuid varchar(36) not null,
    FOREIGN KEY (quote_uuid) REFERENCES quote (uuid),
    FOREIGN KEY (musician_uuid) REFERENCES music (uuid)
);
