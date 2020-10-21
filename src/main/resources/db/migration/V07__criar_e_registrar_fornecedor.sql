CREATE TABLE programacao4trabalho1.fornecedor (
	codigo_fornecedor BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	telefone VARCHAR(10),
    celular VARCHAR(11),    
    email varchar(50) NOT NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;	