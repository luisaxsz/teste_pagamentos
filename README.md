# Teste Pagamentos

[![CI](https://github.com/luisaxsz/teste_pagamentos/actions/workflows/ci.yml/badge.svg)](https://github.com/luisaxsz/teste_pagamentos/actions/workflows/ci.yml)
![Java 21](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-20-DD0031?logo=angular&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-XE_18-F80000?logo=oracle&logoColor=white)

Aplicação full stack para gestão de pagamentos, desenvolvida como desafio técnico de seleção. Permite cadastrar, consultar, filtrar, alterar o status e inativar pagamentos.

## Stack
- **Backend:** Java 21, Spring Boot 3.5, Spring Data JPA, Specification (filtros dinâmicos), ModelMapper
- **Banco:** Oracle XE 18 com migrações **Flyway**
- **Frontend:** Angular 20
- **Infra:** Docker Compose, GitHub Actions

## Endpoints (prefixo `/api`)
| Recurso | Rotas |
|---|---|
| Pagamentos | `GET /pagamentos`, `GET /pagamentos/page` (paginado), `GET /pagamentos/{id}`, `GET /pagamentos/status/{status}`, `POST`, `PUT /{id}`, `PUT /status/{id}`, `PUT /inativar/{id}`, `DELETE /{id}` |
| Status de pagamento | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| Tipo de pagamento | `GET`, `GET /{id}`, `POST` |

## Como executar
Pré-requisitos: Java 21 (`sdk env`), Node 22 (`nvm use`), Maven e Docker.

```bash
# 1. banco Oracle
docker compose up -d

# 2. backend (http://localhost:8080/api)
mvn clean spring-boot:run

# 3. frontend (http://localhost:4200)
npm install
npx ng serve
```

## Pipeline
O GitHub Actions compila e testa o backend e faz o build do frontend a cada push e pull request (`.github/workflows/ci.yml`).
