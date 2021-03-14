CREATE TABLE cont_tipodoc (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(150) NOT NULL	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO cont_tipodoc (descricao) values ('Nota Fiscal');
INSERT INTO cont_tipodoc (descricao) values ('Nota Fiscal Eletrônica');
