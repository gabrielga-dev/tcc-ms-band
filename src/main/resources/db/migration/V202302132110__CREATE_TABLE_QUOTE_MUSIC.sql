create table quote_music
(
    uuid          varchar(36) primary key not null,
    quote_uuid    varchar(36)             not null,
    music_uuid    varchar(36)             not null,
    play_order         int                     not null,
    play_when          varchar(50),
    observation   varchar(500),
    creation_date timestamp               not null,

    FOREIGN KEY (quote_uuid) REFERENCES quote (uuid),
    FOREIGN KEY (music_uuid) REFERENCES music (uuid)
);