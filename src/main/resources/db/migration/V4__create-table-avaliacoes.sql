CREATE TABLE avaliacoes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_usuario BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    comentario TEXT NOT NULL,
    data DATE,
    PRIMARY KEY (id),
    CONSTRAINT fk_usuario_avaliacao
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id),
    CONSTRAINT fk_produto_avaliacao
            FOREIGN KEY (id_produto)
            REFERENCES produtos(id)
);