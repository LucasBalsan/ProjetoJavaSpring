CREATE TABLE compra_produto (
	codigo_compra BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	produto BIGINT(20) NOT NULL,    
	quantidade INT NOT NULL,
    valor FLOAT NOT NULL,
    data_compra DATE NOT NULL,
    fornecedor BIGINT(20) NOT NULL,
    lote VARCHAR(12),
    validade_lote DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE compra_produto ADD CONSTRAINT fk_produto FOREIGN KEY (produto) REFERENCES produto(codigo_produto);
ALTER TABLE compra_produto ADD CONSTRAINT fk_fornecedor FOREIGN KEY (fornecedor) REFERENCES fornecedor(codigo_fornecedor);