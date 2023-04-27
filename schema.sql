CREATE TABLE LIVROS (
    ID_LIVRO INT NOT NULL,
    NOME_LIVRO VARCHAR(100) NOT NULL,
    AUTORIA VARCHAR(100) NOT NULL,
    ID_EDITORA INT NOT NULL,
    CATEGORIA VARCHAR(100) NOT NULL,
    PREÇO DECIMAL(5,2) NOT NULL,  
 PRIMARY KEY (ID_LIVRO)
);

CREATE TABLE ESTOQUE (
    ID_LIVRO INT NOT NULL,
    QTD_ESTOQUE INT NOT NULL,
 PRIMARY KEY (ID_LIVRO)
);

CREATE TABLE VENDAS (
    ID_PEDIDO INT NOT NULL,
    ID_VENDEDOR INT NOT NULL,
    ID_LIVRO INT NOT NULL,
    QTD_VENDIDA INT NOT NULL,
    DATA_VENDA DATE NOT NULL,
 PRIMARY KEY (ID_VENDEDOR,ID_PEDIDO)
);

CREATE TABLE VENDEDORES (
    ID_VENDEDOR INT NOT NULL,
    NOME_VENDEDOR VARCHAR(255) NOT NULL,
 PRIMARY KEY (ID_VENDEDOR)
);

CREATE TABLE EDITORAS (
    ID_EDITORA INT NOT NULL,
    NOME_EDITORA VARCHAR(255) NOT NULL,
 PRIMARY KEY (ID_EDITORA)
);