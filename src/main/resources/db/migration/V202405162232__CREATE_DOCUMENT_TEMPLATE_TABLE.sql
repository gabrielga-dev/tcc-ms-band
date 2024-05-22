CREATE TABLE document_template
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(75) NOT NULL,
    content     TEXT        NOT NULL
);
