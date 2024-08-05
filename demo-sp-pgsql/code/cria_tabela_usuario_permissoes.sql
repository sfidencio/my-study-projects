DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios
(
    id serial NOT NULL,
    nome character varying(255),
    status boolean,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);

CREATE TABLE permissoes
(
    id serial NOT NULL,
    nome character varying(255),
    usuario_id integer,
    CONSTRAINT permissoes_pkey PRIMARY KEY (id),
    --foreign key
    CONSTRAINT fk_permissoes_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);