# FuriaChat, O chatbot da Furia!

Este projeto consiste em uma **landing page interativa com um chatbot inteligente** focado em fornecer informações atualizadas sobre a equipe de e-sports **FURIA**. A interface é simples, acessível e responsiva, e permite que o usuário converse com o bot para saber sobre **transferências, histórico de jogadores e curiosidades recentes**.

---

## Sumário

- [Objetivo](#-objetivo)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como Funciona o ChatBot](#-como-funciona-o-chatbot)
- [Estrutura dos Dados no MongoDB](#️-estrutura-dos-dados-no-mongodb)
- [Funcionalidades](#-funcionalidades)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Contato](#-contato)

---

## Objetivo

Criar uma interface web com chatbot que utiliza a **API da OpenAI (gpt-4o-mini)** para responder perguntas sobre a FURIA, mesmo que o modelo da OpenAI **não tenha informações atualizadas**.

---

## Tecnologias Utilizadas

### Backend

- **Java Spring Boot**
  - Integração com MongoDB.
  - Comunicação com a API da OpenAI.
  - CRUD para prompts personalizados.
  - Roteamento seguro para o frontend.

- **MongoDB**
  - Banco de dados NoSQL para armazenar os prompts programados.
  - Cada prompt é mapeado com `id`, `title` e `content`.

- **OpenAI API**
  - Modelo utilizado: `gpt-4o-mini`.
  - A inteligência do chatbot é estendida com contexto adicional via prompts do banco.

### Frontend

- **Angular**
  - Interface moderna e responsiva.
  - Componente de chat.
  - Conexão com o backend via HTTP.
  - Exibição da landing page com foco em conversão/atração.

---

## Como Funciona o ChatBot

O modelo `gpt-4o-mini`, por padrão, **não possui dados atualizados sobre a FURIA**, como:
- Contratações recentes
- Saídas de jogadores
- Histórico pós-2023

### Solução implementada:

Foi criado um sistema de **injeção de contexto** via banco MongoDB, com a seguinte abordagem:

1. Prompts personalizados são salvos no MongoDB com estrutura:
   ```json
   {
     "_id": "xxx",
     "title": "Historia do Fallen",
     "content": "O jogador FalleN fez história no CS brasileiro quando em 2016..."
   }

## Contato

Caso tenha dúvidas ou sugestões, entre em contato:

- **Developer:** [Lucas Fend Ribeiro]
- **E-mail:** [lucasribeiro.developer@outlook.com]
- **LinkedIn:** [https://www.linkedin.com/in/lucasribfend/](https://www.linkedin.com/in/lucasribfend/)

---
