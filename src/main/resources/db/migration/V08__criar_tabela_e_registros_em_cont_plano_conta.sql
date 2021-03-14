CREATE TABLE cont_planoconta (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	conta_pai VARCHAR(10) NULL,
	conta VARCHAR(50) NOT NULL,
	descricao VARCHAR(80) NOT NULL,
	tipo_lanc VARCHAR(1) NOT NULL,
	profundidade VARCHAR(1) NOT NULL,
	ano_exercicio VARCHAR(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO cont_planoconta (conta, descricao,  profundidade, tipo_lanc,  ano_exercicio) 
		values ('3', 'Receitas Correntes', 'S', 'R', '2016');
INSERT INTO cont_planoconta (conta_pai, conta, descricao,  profundidade, tipo_lanc,  ano_exercicio) 
		values ('3', '3.1', 'Receitas Gerais', 'S', 'R', '2016');
INSERT INTO cont_planoconta (conta_pai, conta, descricao,  profundidade, tipo_lanc,  ano_exercicio) 
		values ('3.1', '3.1.1', 'Receitas Ordinárias da Atividade', 'S', 'R', '2016');