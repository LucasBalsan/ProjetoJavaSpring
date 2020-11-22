-- Usuarios
INSERT INTO usuario (codigo, nome, email, senha) VALUES (1, 'Administrador', 'admin@teste.com', 'senha1');
INSERT INTO usuario (codigo, nome, email, senha) VALUES (2, 'Lucas', 'lucas@teste.com', 'senha2');
INSERT INTO usuario (codigo, nome, email, senha) VALUES (3, 'Guilherme', 'gui@teste.com', 'senha3');

-- Role de Marca
INSERT INTO permissao (codigo, descricao) VALUES (1, 'ROLE_CADASTRAR_MARCA');
INSERT INTO permissao (codigo, descricao) VALUES (2, 'ROLE_PESQUISAR_MARCA');
INSERT INTO permissao (codigo, descricao) VALUES (3, 'ROLE_REMOVER_MARCA');


-- Role de Pessoa
INSERT INTO permissao (codigo, descricao) VALUES (4, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (5, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (6, 'ROLE_REMOVER_PESSOA');


-- Role de Categoria
INSERT INTO permissao (codigo, descricao) VALUES (7, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (codigo, descricao) VALUES (8, 'ROLE_PESQUISAR_CATEGORIA');
INSERT INTO permissao (codigo, descricao) VALUES (9, 'ROLE_REMOVER_CATEGORIA');

-- Role de Fornecedor
INSERT INTO permissao (codigo, descricao) VALUES (10, 'ROLE_CADASTRAR_FORNECEDOR');
INSERT INTO permissao (codigo, descricao) VALUES (11, 'ROLE_PESQUISAR_FORNECEDOR');
INSERT INTO permissao (codigo, descricao) VALUES (12, 'ROLE_REMOVER_FORNECEDOR');


-- Administrador
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 12);


-- Lucas (Todas as Permissoes)
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 12);


-- Guilherme (Apenas Leitura)
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (3, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (3, 5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (3, 8);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (3, 11);
