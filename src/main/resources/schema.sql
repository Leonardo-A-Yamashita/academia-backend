CREATE TABLE planos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        descricao VARCHAR(255) NOT NULL,
                        preco DOUBLE NOT NULL,
                        ativo BOOLEAN NOT NULL
);