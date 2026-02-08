# Gerenciamento de Tarefas

Sistema monolítico: API REST em Spring Boot + frontend React servido pela própria aplicação.

## Visão Geral

- Backend: Java 17 + Spring Boot
- Frontend: React (build estático)
- Persistência: Spring Data JPA + H2 (memória)
- Validação: Bean Validation
- Segurança: Spring Security com perfis `dev` (liberado) e `prod` (básico)

## Estrutura DDD (Backend)

- `src/main/java/com/example/gerenciamentotarefas/domain` - Entidades e contratos do domínio
- `src/main/java/com/example/gerenciamentotarefas/application/usecase` - Casos de uso
- `src/main/java/com/example/gerenciamentotarefas/infra` - Implementações técnicas (JPA, config)
- `src/main/java/com/example/gerenciamentotarefas/api` - Controllers, DTOs e mappers

## Estrutura do Frontend

- `src/main/javascript` - Projeto React (JavaScript)
- `src/main/resources/static` - Build do frontend servido pelo Spring

## Requisitos

- Java 17
- Maven (ou use o wrapper `./mvnw`)
- Node.js (LTS) para build do frontend

## Como Rodar (Monolítico)

### 1) Rodar em desenvolvimento

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Abra no navegador:

```text
http://localhost:8080
```

API:

```text
http://localhost:8080/api/tarefas
```

Console do H2 (apenas no `dev`):

```text
http://localhost:8080/h2-console
```

Configurações H2 (dev):

```text
JDBC URL: jdbc:h2:mem:gerenciamentotarefas
User Name: sa
Password:
```

### 2) Rodar em produção (modo demo)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

Credenciais básicas (alterar em `application-prod.properties`):

```text
User: admin
Password: admin
```

## Build do Monolito (backend + frontend)

O build do React é executado automaticamente pelo Maven.

```bash
./mvnw clean package
```

O artefato final fica em `target/`.

## Desenvolvimento do Frontend (opcional)

```bash
cd src/main/javascript
npm install
npm start
```

A aplicação ficará disponível em:

```text
http://localhost:3000
```

Se quiser apontar para outra API, ajuste `REACT_APP_API_URL`.

## Endpoints

- `GET /api/tarefas`
- `GET /api/tarefas/{id}`
- `POST /api/tarefas`
- `PUT /api/tarefas/{id}`
- `PATCH /api/tarefas/{id}/concluir`
- `DELETE /api/tarefas/{id}`

Parâmetros opcionais em `GET /api/tarefas`:
- `titulo`
- `status`
- `prioridade`
- `page`
- `size`

## Estrutura do Payload

Campos válidos para `status`:
- `PENDENTE`
- `EM_ANDAMENTO`
- `CONCLUIDA`

Campos válidos para `prioridade`:
- `BAIXA`
- `MEDIA`
- `ALTA`

Exemplo de criação:

```json
{
  "titulo": "Estudar Spring",
  "descricao": "Revisar DTO e validação",
  "status": "PENDENTE",
  "prioridade": "ALTA"
}
```

## Observações

- Em `dev` o banco H2 é em memória. Ao reiniciar a aplicação, os dados são perdidos.
- Em `prod` o H2 é arquivo local (`./data`). Para persistência real, troque o banco e ajuste o `application-prod.properties`.
- Para rodar testes: `./mvnw test`.
# Gerenciado-de-Tarefas

