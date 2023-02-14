create table musician_participation
(
    uuid           varchar(36) primary key not null,
    event_uuid     varchar(36)             not null,
    musician_uuid  varchar(36)             not null,
    value          DECIMAL(10, 2)          not null,
    payment_method varchar(50)             not null,
    creation_date  timestamp               not null,
    update_date    timestamp,
    FOREIGN KEY (musician_uuid) REFERENCES musician (uuid)
);