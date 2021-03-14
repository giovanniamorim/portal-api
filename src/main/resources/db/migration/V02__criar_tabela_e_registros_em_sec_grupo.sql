CREATE TABLE sec_grupo (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sec_grupo (id, descricao) values (1, 'Sindicalizado');
INSERT INTO sec_grupo (id, descricao) values (2, 'Administrador');
