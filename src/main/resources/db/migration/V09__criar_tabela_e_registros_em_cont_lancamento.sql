CREATE TABLE cont_lancamento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tipo VARCHAR(20) NOT NULL,
	data_pagamento DATE,
	planoconta_id BIGINT(20) NOT NULL,
	valor DECIMAL(10,2) NOT NULL,
	modopag_id BIGINT(20) NOT NULL,
	tipodoc_id BIGINT(20) NOT NULL,
	num_doc VARCHAR(20) NOT NULL,
	num_cheque VARCHAR(30) NOT NULL,
	observacoes VARCHAR(150) NOT NULL,
	ano_exercicio VARCHAR(10) NOT NULL,
	data_cadastro DATETIME NOT NULL,
	
	FOREIGN KEY (planoconta_id) REFERENCES cont_planoconta(id),
	FOREIGN KEY (modopag_id) REFERENCES cont_modopag(id),
	FOREIGN KEY (tipodoc_id) REFERENCES cont_tipodoc(id)	
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO cont_lancamento (tipo, data_pagamento, valor, num_doc, num_cheque, observacoes, ano_exercicio, data_cadastro, planoconta_id, modopag_id, tipodoc_id) 
		values ('D', '2017-06-10', 6500.00, '1020', 'CH2312', 'Pagamento referente ao site', '2020', NOW(), 1, 1, 1);
