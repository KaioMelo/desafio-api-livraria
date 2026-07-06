# 📚 Livraria API — Desafio Técnico Backend

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v21-red.svg" />
    </a>
   <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring Boot-v3.5.16-Lime.svg" />
    </a>
    <a alt="Spring Security">
        <img src="https://img.shields.io/badge/Spring Security-v3.5.16-Lime.svg" />
    </a>
   <a alt="Spring Test">
        <img src="https://img.shields.io/badge/Spring Test-v3.5.16-Lime.svg" />
    </a>
   <a alt="Spring Doc">
        <img src="https://img.shields.io/badge/Spring Doc-v3.5.16-Lime.svg" />
    </a>
    <a alt="Maven">
        <img src="https://img.shields.io/badge/Maven-v4.0.0-darkblue.svg" />
    </a>
    <a alt="Banco H2">
        <img src="https://img.shields.io/badge/Banco H2-v0.12.6-blue.svg" />
    </a>
</p>


Esta é uma API REST desenvolvida para o gerenciamento de um catálogo de livros. O projeto foi construído seguindo as melhores práticas de arquitetura em camadas, tratamento global de exceções, validação de dados e cobertura de testes automatizados.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* **Java 21** instalado.
* **Maven 3.x** instalado (ou uso do `mvnw` incluso).
* Uma IDE de sua preferência (Eclipse, STS, IntelliJ, VS Code).

### Passo a Passo

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/KaioMelo/desafio-api-livraria.git
   cd desafio-api-livraria
   ```
2. **Compilar e baixar as dependências:**
    ```Bash
    mvn clean install
    ```
3. **Executar a aplicação:**
  Via Terminal:
    ```Bash
    mvn spring-boot:run
    ```

  Via IDE: Abra a classe principal LivrariaApplication.java (localizada em src/main/java/com/br/desafio/dev/jr/) e execute como Java Application.

A aplicação estará disponível em: http://localhost:8080

## 🧠 Decisões Técnicas & Justificativas

A arquitetura e as ferramentas deste projeto foram escolhidas visando performance, facilidade de manutenção e padrões exigidos pelo mercado:

* **Java 21:** Escolhido por ser a versão LTS (Long Term Support) mais estável e moderna do ecossistema Java, trazendo melhorias significativas de performance e suporte a recursos avançados da linguagem.
* **Spring Boot 3.5.x:** Utilizado para acelerar o desenvolvimento através da configuração automática, fornecendo uma base robusta e de mercado para APIs REST com os módulos `Spring Web` e `Spring Data JPA`.
* **Banco de Dados H2 (In-Memory):** Adotado por ser uma solução ideal para desafios técnicos. Ele elimina a necessidade de o avaliador configurar um banco de dados local (como PostgreSQL ou MySQL), facilitando o *setup* imediato do projeto com dados isolados que resetam a cada execução.
* **Springdoc OpenAPI (Swagger):** Incluído para gerar automaticamente a documentação visual e interativa das rotas, permitindo validar e testar todos os endpoints diretamente pelo navegador.
* **Arquitetura em Camadas:** O projeto foi estruturado estritamente seguindo a divisão de responsabilidades em `Controller` (exposição dos endpoints HTTP), `Service` (camada isolada para regras de negócio), `Repository` (interface de comunicação com o banco via JPA) e `Exception` (centralização e tratamento global de erros). Isso garante alta coesão, baixo acoplamento e facilidade na escrita de testes unitários e de integração.

## 🛠️ Como Testar a Aplicação

O projeto conta com duas abordagens complementares de testes: manuais (interface visual) e automatizados.

### 1. Testes Manuais e Documentação (Swagger UI)
Com a aplicação rodando, acesse a interface interativa do Swagger para realizar requisições em tempo real:
👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 2. Testes Automatizados (JUnit 5 + MockMvc)
Foram desenvolvidos testes de integração focados nos Controllers para garantir o funcionamento correto de todas as rotas da API (Caminhos Felizes e Infelizes, como validações e erros 404).

* **Para rodar via terminal:**
  ```bash
  mvn test
  ```

### 3. Acesso ao Banco de Dados (Console H2)
Como o projeto utiliza o banco de dados H2 em memória, você pode inspecionar as tabelas e os dados salvos em tempo real pelo navegador:
1. Com a aplicação rodando, verifique o console do terminal/IDE e localize a linha de log do H2 que contém a chave aleatória gerada para a sessão, parecida com esta:
   `Database available at 'jdbc:h2:mem:uma-chave-aleatoria-aqui'`
2. Copie a URL completa gerada (incluindo a chave).
3. Abra o seu navegador e acesse: 
   `http://localhost:8080/h2-console`
4. No campo **JDBC URL**, cole a URL exata que você copiou do console.
5. Deixe o campo **User Name** como `sa`, a senha em branco e clique em **Connect**.

## 📋 Documentação das Rotas da API (`livro-controller`)

Todos os endpoints utilizam o prefixo padronizado `/api/livros`. Os payloads de entrada e saída trafegam em formato **JSON**.

| Método | Endpoint | Descrição | Status Sucesso | Status Erro Esperado |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/livros` | Cadastra um novo livro no sistema. | `201 Created` | `400 Bad Request` (Validação) |
| **GET** | `/api/livros` | Lista todos os livros cadastrados no banco H2. | `200 OK` | — |
| **GET** | `/api/livros/{id}` | Busca os detalhes de um livro específico pelo ID. | `200 OK` | `404 Not Found` (ID inexistente) |
| **PUT** | `/api/livros/{id}` | Atualiza todos os dados de um livro existente. | `200 OK` | `404 Not Found` ou `400 Bad Request` |
| **DELETE** | `/api/livros/{id}` | Remove um livro do catálogo pelo ID. | `200 OK` | `404 Not Found` (ID inexistente) |

### Exemplo de Payload (JSON) para POST / PUT:
```json
{
  "titulo": "O Senhor dos Anéis",
  "autor": "J.R.R. Tolkien",
  "anoPublicacao": 1954
}
```

## 👤 Autor

* **Nome:** Kaio Melo dos Santos
* **LinkedIn:** [linkedin.com/in/kaiomelo/](https://www.linkedin.com/in/kaiomelo/)
* **E-mail:** kaiomelo.dev@gmail.com
