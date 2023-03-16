CREATE TABLE ctb_lancamento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tipo_lancamento VARCHAR(10) NOT NULL,
	data_lancamento DATE,
	plano_conta_id BIGINT(20) NOT NULL,
	valor DECIMAL(19,2) NOT NULL,
	modo_pagamento VARCHAR(50) NOT NULL,
	tipo_comprovante VARCHAR(50) NOT NULL,
	num_doc VARCHAR(30),
	num_cheque VARCHAR(30),
	obs VARCHAR(255) NOT NULL,
	sup_caixa VARCHAR(5) NOT NULL,
	file_url VARCHAR(255) NOT NULL,
	ano_exercicio INT(5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE ctb_lancamento
    ADD FOREIGN KEY (plano_conta_id) REFERENCES ctb_plano_conta(id);


INSERT INTO ctb_lancamento (id, tipo_lancamento, data_lancamento, plano_conta_id, valor, modo_pagamento, tipo_comprovante, obs, sup_caixa, file_url)
    values (1, 'Receita', '2017-06-10', 4, 15, 'PIX', 'Nota Fiscal', 'Pagamento de corrida de Uber', 'Sim', 'teste.jpg');

INSERT INTO ctb_lancamento (id, tipo_lancamento, data_lancamento, plano_conta_id, valor, modo_pagamento, tipo_comprovante, obs, sup_caixa, file_url)
    values (2, 'Receita', '2017-06-10', 4, 2562.63, 'CHEQUE', 'Nota Fiscal', 'Compra de Mesa para cozinha', 'Sim', 'teste.jpg');

