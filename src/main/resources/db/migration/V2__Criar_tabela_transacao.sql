CREATE TABLE IF NOT EXISTS transacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    ativo_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL CHECK(quantidade >= 1),
    preco DECIMAL(18, 2) NOT NULL CHECK(preco >= 0.01),
    data_hora TIMESTAMP NOT NULL,
    corretora VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    CONSTRAINT fk_transacao_ativo FOREIGN KEY(ativo_id) REFERENCES ativo(id) ON DELETE CASCADE
);

CREATE INDEX idx_transacao_usuario_id ON transacao(usuario_id);
CREATE INDEX idx_transacao_ativo_id ON transacao(ativo_id);
CREATE INDEX idx_transacao_usuario_ativo ON transacao(usuario_id, ativo_id);
