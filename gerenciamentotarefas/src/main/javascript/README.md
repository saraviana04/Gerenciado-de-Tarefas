# Gerenciamento de Tarefas - Frontend

Aplicação frontend em React que é compilada e servida pelo Spring Boot.

## Requisitos

- Node.js (LTS)
- npm ou yarn

## Desenvolvimento Local

```bash
npm install
npm start
```

A aplicação ficará disponível em:

```text
http://localhost:3000
```

A API deve estar rodando em:

```text
http://localhost:8080/api/tarefas
```

Se mudar a porta do backend, ajuste a variável `REACT_APP_API_URL` ou atualize o `proxy` em `package.json`.

## Build para o Backend (Monolítico)

```bash
npm run build
```

O Maven copia o build automaticamente durante o `package`.

## Scripts Úteis

- `npm start` - inicia o servidor de desenvolvimento
- `npm test` - executa os testes
- `npm run build` - gera build de produção
