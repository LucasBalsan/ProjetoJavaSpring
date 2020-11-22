CREATE TABLE produto (
	codigo_produto BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	peso FLOAT,
    codigo_barras BIGINT(20) NOT NULL,
    categoria BIGINT(20),
    marca BIGINT(20),
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;	

ALTER TABLE produto ADD CONSTRAINT fk_categoria FOREIGN KEY (categoria) REFERENCES categoria(codigo_categoria);
ALTER TABLE produto ADD CONSTRAINT fk_marca FOREIGN KEY (marca) REFERENCES marca(codigo_marca);