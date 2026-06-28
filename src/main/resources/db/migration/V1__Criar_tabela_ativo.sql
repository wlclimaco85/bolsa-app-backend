CREATE TABLE IF NOT EXISTS ativo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ticker VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    setor VARCHAR(100) NOT NULL,
    preco_atual DECIMAL(18, 2) NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,
    CONSTRAINT uk_ativo_ticker UNIQUE(ticker)
);

CREATE INDEX idx_ativo_ticker ON ativo(ticker);
