#BAND
CREATE TABLE band
(
    uuid                varchar(36) primary key not null,
    name                varchar(100)            not null,
    description         varchar(500)            not null,
    active              BOOLEAN                 not null,
    owner_uuid          varchar(36)             not null,
    creation_date       timestamp               not null,
    update_date         timestamp,
    avatar_picture_uuid varchar(36)
);

create table band_address
(
    uuid       varchar(36) primary key not null,
    street     varchar(50)             not null,
    neighbour  varchar(50)             not null,
    number     int                     not null,
    complement varchar(10),
    city       varchar(50)             not null,
    state      varchar(50)             not null,
    country    varchar(50)             not null,
    zip_code   varchar(25)             not null,
    latitude   DECIMAL(10, 8),
    longitude  DECIMAL(10, 8),
    band_uuid  varchar(36)             not null,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);

create table contact
(
    uuid          varchar(36) primary key not null,
    band_uuid     varchar(36)             not null,
    type          varchar(50)             not null,
    content       varchar(150)            not null,
    creation_date timestamp               not null,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);

#MUSICIAN_TYPE
create table musician_type
(
    uuid        varchar(36) primary key not null,
    name        varchar(50)             not null,
    description varchar(500)
);

#MUSICIAN
create table musician
(
    uuid                varchar(36) primary key not null,
    person_uuid         varchar(36),
    first_name          varchar(75)             not null,
    last_name           varchar(150)            not null,
    birthday            timestamp               not null,
    cpf                 varchar(14)             not null unique,
    email               varchar(100)            not null,
    band_uuid           varchar(36),
    creation_date       timestamp               not null,
    update_date         timestamp,
    active              boolean                 not null default false,
    avatar_picture_uuid varchar(36),
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);


create table musician_address
(
    uuid          varchar(36) primary key not null,
    street        varchar(50)             not null,
    neighbour     varchar(50)             not null,
    number        int                     not null,
    complement    varchar(10),
    city          varchar(50)             not null,
    state         varchar(50)             not null,
    country       varchar(50)             not null,
    zip_code      varchar(25)             not null,
    latitude      DECIMAL(10, 8),
    longitude     DECIMAL(10, 8),
    musician_uuid varchar(36)             not null,
    FOREIGN KEY (musician_uuid) REFERENCES musician (uuid)
);

create table musician_musician_type
(
    musician_uuid      varchar(36) not null,
    musician_type_uuid varchar(36) not null,
    FOREIGN KEY (musician_uuid) REFERENCES musician (uuid),
    FOREIGN KEY (musician_type_uuid) REFERENCES musician_type (uuid)
);

create table band_musician
(
    band_uuid     varchar(36) not null,
    musician_uuid varchar(36) not null,
    creation_date timestamp   not null,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid),
    FOREIGN KEY (musician_uuid) REFERENCES musician (uuid)
);

#MUSIC
create table music
(
    uuid          varchar(36) primary key not null,
    band_uuid     varchar(36),
    name          varchar(60)             not null,
    author        varchar(60)             not null,
    artist        varchar(60)             not null,
    observation   varchar(1000),
    creation_date timestamp               not null,
    update_date   timestamp,
    active        boolean                 not null default true,
    FOREIGN KEY (band_uuid) REFERENCES band (uuid)
);

#QUOTE_REQUEST
create table quote_request
(
    uuid          varchar(36) primary key not null,
    event_uuid    varchar(36)             not null,
    band_uuid     varchar(36)             not null,
    description   varchar(1000)           not null,
    creation_date timestamp               not null,
    update_date   timestamp,
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
