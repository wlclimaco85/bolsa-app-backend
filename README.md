# Bolsa App Backend

Backend da aplicação de Bolsa de Valores - Spring Boot 9002

## Setup

```bash
cd bolsa-app-backend
mvn clean install
mvn spring-boot:run
```

Servidor rodará em: **http://localhost:9002**

## Endpoints

### Carteira
- **GET** `/api/bolsa/carteira` — Retorna 3 holdings: PETR4, VALE3, WEGE3

### Ativos
- **GET** `/api/bolsa/ativos` — Retorna 10 ativos do mercado

### Transações
- **POST** `/api/bolsa/transacao` — Cria transação (compra/venda)
  ```json
  {
    "simbolo": "PETR4",
    "quantidade": 100,
    "preco": "28.50",
    "tipo": "COMPRA"
  }
  ```

### Performance
- **GET** `/api/bolsa/performance` — Retorna performance dos últimos 6 meses

### Sugestões
- **GET** `/api/bolsa/sugestoes` — Retorna 3 ativos sugeridos

## Testes

```bash
mvn test
```

**Status:** 5/5 testes passando ✓

## Segurança

SecurityConfig permite acesso anônimo a `/api/**`. Para produção, integrar com AppAcademia:9001 para validação remota de token.
