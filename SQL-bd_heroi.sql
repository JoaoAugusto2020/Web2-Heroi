CREATE DATABASE IF NOT EXISTS `bd_heroi`;

CREATE TABLE IF NOT EXISTS `heroi` (
  `id_heroi` bigint NOT NULL AUTO_INCREMENT,
  `ano_origem` int DEFAULT NULL,
  `ataque` int NOT NULL,
  `defesa` int NOT NULL,
  `nome` varchar(255) NOT NULL,
  `universo` varchar(255) DEFAULT NULL,
  `vida` int NOT NULL,
  PRIMARY KEY (`id_heroi`)
);

INSERT INTO heroi (nome, vida, defesa, ataque, universo, ano_origem)
VALUES ("Batman", 2500, 500, 200, "DC", 1950);

SELECT * FROM heroi;