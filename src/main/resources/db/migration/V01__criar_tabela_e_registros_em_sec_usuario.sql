CREATE TABLE sec_usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR (150) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	avatar TINYBLOB NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sec_usuario (id, nome, email, senha,ativo) values (
	1,
	'Giovanni Amorim',
	'giovanni@sis7.com.br', 
	'$2a$10$iq9uRVX.9EhJIaR5zYv.pOFiylUvH0fM5YuJ04Zexev4CelOrqBN.',
	true
	);
