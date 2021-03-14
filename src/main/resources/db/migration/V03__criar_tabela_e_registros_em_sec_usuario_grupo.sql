CREATE TABLE sec_usuario_grupo (
	id_usuario BIGINT(20),
	id_grupo BIGINT(20),
	
	FOREIGN KEY (id_usuario) REFERENCES sec_usuario(id),
	FOREIGN KEY (id_grupo) REFERENCES sec_grupo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sec_usuario_grupo (id_usuario, id_grupo) values (1, 1);
