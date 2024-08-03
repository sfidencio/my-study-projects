DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios
(
    id serial NOT NULL,
    nome character varying(255),
    status boolean,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
)