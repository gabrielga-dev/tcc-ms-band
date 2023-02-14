create table music_event_use
(
    uuid          varchar(36) primary key not null,
    event_uuid    varchar(36)             not null,
    music_uuid    varchar(36)             not null,
    play_order    int                     not null,
    play_when     varchar(50),
    observation   varchar(500),
    creation_date timestamp               not null,
    update_date   timestamp,
    FOREIGN KEY (music_uuid) REFERENCES music (uuid)
);