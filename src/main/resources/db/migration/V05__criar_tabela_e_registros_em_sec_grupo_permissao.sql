CREATE TABLE sec_grupo_permissao (
	id_grupo BIGINT(20),
	id_permissao BIGINT(20),
	
	FOREIGN KEY (id_grupo) REFERENCES sec_grupo(id),
	FOREIGN KEY (id_permissao) REFERENCES sec_permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sec_grupo_permissao (id_grupo, id_permissao) values (1, 1);
