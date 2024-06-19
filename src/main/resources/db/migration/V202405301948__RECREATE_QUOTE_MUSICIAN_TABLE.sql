DROP TABLE quote_musician;

create table quote_musician
(
    quote_uuid    varchar(36) not null,
    musician_uuid varchar(36) not null,
    FOREIGN KEY (quote_uuid) REFERENCES quote (uuid),
    FOREIGN KEY (musician_uuid) REFERENCES musician (uuid)
);
