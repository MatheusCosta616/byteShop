-- ===========================================
-- 1) CRIAR O SCHEMA (DATABASE)
-- ===========================================
CREATE SCHEMA IF NOT EXISTS byteshop_catalogo
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_0900_ai_ci;


-- (Opcional, mas recomendado no Workbench:)
USE byteshop_catalogo;

-- ===========================================
-- 2) TABELAS
-- ===========================================

-- 2) Tabelas essenciais

CREATE TABLE IF NOT EXISTS produto (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(120) NOT NULL,
preco DECIMAL(10,2) NOT NULL,
categoria VARCHAR(60),
descricao TEXT,
ativo BOOLEAN NOT NULL DEFAULT TRUE
);


INSERT INTO produto (nome, preco, categoria, descricao, ativo) VALUES
('Mouse Óptico USB', 49.90, 'periférico', 'Mouse básico com 1000 DPI', TRUE),
('Teclado Mecânico', 299.00, 'periférico', 'Switch Red, ABNT2', TRUE),
('Headset USB', 159.90, 'áudio', 'Fone com microfone, conforto leve', TRUE),
('Monitor 24" Full HD', 799.00, 'monitor', 'Painel IPS, 75Hz', TRUE),
('SSD 480GB', 229.90, 'armazenamento', 'SATA III, bom custo-benefício', TRUE);


select * from byteshop_catalogo.produto;