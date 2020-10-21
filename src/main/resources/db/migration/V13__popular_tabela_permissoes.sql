INSERT INTO usuario (codigo, nome, email, senha) VALUES (1, 'Administrador', 'admin@teste.com', 'senha1');
INSERT INTO usuario (codigo, nome, email, senha) VALUES (2, 'Lucas', 'lucas@teste.com', 'senha2');

INSERT INTO permissao (codigo, descricao) VALUES (1, 'ROLE_CADASTRAR_MARCA');
INSERT INTO permissao (codigo, descricao) VALUES (2, 'ROLE_PESQUISAR_MARCA');

INSERT INTO permissao (codigo, descricao) VALUES (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (4, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (codigo, descricao) VALUES (5, 'ROLE_REMOVER_PESSOA');

-- Administrador
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 3);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (1, 5);

-- Lucas

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES (2, 4);



