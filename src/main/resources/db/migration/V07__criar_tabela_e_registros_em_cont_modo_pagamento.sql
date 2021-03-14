CREATE TABLE cont_modopag (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(150) NOT NULL	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO cont_modopag (descricao) values ('Cartão de Crédito');
INSERT INTO cont_modopag (descricao) values ('Cheque');
