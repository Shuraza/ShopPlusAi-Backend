CREATE TABLE produtos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    id_categoria BIGINT NOT NULL,
    preco DOUBLE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (id_categoria)
        REFERENCES categorias(id)
);