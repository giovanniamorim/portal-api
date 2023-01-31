CREATE TABLE ctb_balancete (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	ano INT(4) NOT NULL,
	mes VARCHAR(30) NOT NULL,
	file_url VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO ctb_balancete (id, ano, mes, file_url) values (1, 2022, 'Janeiro', 'teste.pdf');
INSERT INTO ctb_balancete (id, ano, mes, file_url) values (2, 2022, 'Fevereiro', 'teste2.pdf');

