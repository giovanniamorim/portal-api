CREATE TABLE ctb_balanco (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	ano INT(4) NOT NULL,
	mes VARCHAR(30) NOT NULL,
	file_url VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO ctb_balanco (id, ano, mes, file_url) values (1, 2022, 'Janeiro', 'teste.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (2, 2022, 'Fevereiro', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (3, 2022, 'Março', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (4, 2022, 'Abril', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (5, 2022, 'Maio', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (6, 2022, 'Junho', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (7, 2022, 'Julho', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (8, 2022, 'Agosto', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (9, 2022, 'Setembro', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (10, 2022, 'Outubro', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (11, 2022, 'Novembro', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (12, 2022, 'Dezembro', 'teste2.pdf');
INSERT INTO ctb_balanco (id, ano, mes, file_url) values (13, 2023, 'Janeiro', 'teste2.pdf');

