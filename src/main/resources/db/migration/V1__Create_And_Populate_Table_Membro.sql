
CREATE DATABASE IF NOT EXISTS `myecclesia-api`;

CREATE TABLE IF NOT EXISTS `tb_membros` (
                                            `id` binary(16) NOT NULL,
    `cpf` varchar(255) DEFAULT NULL,
    `cargo` int DEFAULT NULL,
    `nome` varchar(255) DEFAULT NULL,
    `senha` varchar(255) DEFAULT NULL,
    `telefone` varchar(255) DEFAULT NULL,
    `cargo_code` int DEFAULT NULL,
    `data_nascimento` date DEFAULT NULL,
    `status` int NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `tb_membros` (`id`, `cpf`, `cargo`, `nome`, `senha`, `telefone`, `cargo_code`, `data_nascimento`, `status`) VALUES
                                                                                                                            (_binary 0x03b4a721e5bb47b2b3c7d2e8c47bfcdb, '333.333.333-33', 3, 'Válber Guedes', 'vaiCorinthians1910!', '(83) 9 9275-0932', NULL, NULL, 1),
                                                                                                                            (_binary 0x4ce3541b6fae4907bea1c78e19668160, '222.222.222-22', 4, 'Larissa Rosy', 'amoContabeis2010', '(83) 9 9984-5420', NULL, '1990-03-15', 1),
                                                                                                                            (_binary 0x5017d649ac804bc9bf3d03001551c779, '111.111.111-11', 1, 'Héber Netwon Elias', 'heberJosue87@', '(83) 9 9135-6491', NULL, '1987-01-27', 1),
                                                                                                                            (_binary 0x62cc2351ee51412d8c7063c8037ea81e, '121.121.121-12', 5, 'Isleny Lisboa', 'islenyRicardo97@', '(83) 9 9318-3912', NULL, '1997-01-23', 1),
                                                                                                                            (_binary 0xaf2f0497251c4feaa3bcd046426bda89, '123.123.123-12', 2, 'Severino Vicente', 'rosemary15', '(83) 9 9241-6261', NULL, NULL, 1),
                                                                                                                            (_binary 0xdb771f2cb27d49f7bbd99a9da0c026d0, '222.333.444-55', 1, 'Leonardo Rodrigues', 'leoLeviRavi12!', '(83) 9 8732-5319', 1, NULL, 1);

