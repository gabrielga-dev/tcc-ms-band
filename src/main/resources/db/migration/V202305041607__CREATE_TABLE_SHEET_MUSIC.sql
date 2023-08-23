create table sheet_music
(
    uuid        varchar(36) primary key not null,
    file_uuid   varchar(36)             not null,
    music_uuid  varchar(36)             not null,
    observation varchar(1000),

    FOREIGN KEY (music_uuid) REFERENCES music (uuid)
);
