CREATE TABLE CLIENTE (
    ID INT NOT NULL AUTO_INCREMENT,
    NOME VARCHAR(255) NOT NULL,
    CPF VARCHAR(11) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE SEQUENCE CLIENTE_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE PRODUTO (
    ID INT NOT NULL AUTO_INCREMENT,
    DESCRICAO VARCHAR(255) NOT NULL,
    PRECO DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE SEQUENCE PRODUTO_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE PEDIDO (
    ID INT NOT NULL AUTO_INCREMENT,
    DATA_PEDIDO DATE NOT NULL DEFAULT CURRENT_DATE,
    HORA_PEDIDO TIME NOT NULL DEFAULT CURRENT_TIME,
    DESCRICAO VARCHAR(255) NOT NULL,
    VALOR_TOTAL DECIMAL(10,2) NOT NULL,
    CLIENTE_ID INT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE(ID)
);

CREATE SEQUENCE PEDIDO_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE ITEM_PEDIDO (
    ID INT NOT NULL AUTO_INCREMENT,
    PEDIDO_ID INT NOT NULL,
    PRODUTO_ID INT NOT NULL,
    QUANTIDADE NUMERIC(10,2) NOT NULL,
    VALOR_ITEM DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (PEDIDO_ID) REFERENCES PEDIDO(ID),
    FOREIGN KEY (PRODUTO_ID) REFERENCES PRODUTO(ID)
);

CREATE SEQUENCE ITEM_PEDIDO_SEQ START WITH 1 INCREMENT BY 1;

