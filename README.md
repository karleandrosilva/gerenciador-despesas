# 💰 Gerenciador de Despesas - API REST

Projeto de web service REST desenvolvido para a disciplina de Sistemas Distribuídos do curso de Ciência da Computação da Universidade Federal de Alagoas, campus Arapiraca. A aplicação consiste em um sistema de gerenciamento de despesas em backend, fornecendo as operações principais de um CRUD (Create, Read, Update, Delete).

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 4.0.6** (Web, DevTools)
* **Maven** (Gerenciador de Dependências)

## ⚙️ Pré-requisitos e Execução

Para rodar este projeto localmente, você precisará ter o **Java JDK 21** instalado na sua máquina.

1. Clone o repositório:

   git clone https://github.com/karleandrosilva/gerenciador-despesas.git

2. Navegue até o diretório do projeto:
   
   cd gerenciador-despesas

3. Execute o servidor utilizando o Maven Wrapper (na raiz do projeto):
   * No Windows: `.\mvnw spring-boot:run`
   * No Linux/Mac: `./mvnw spring-boot:run`

O servidor será iniciado na porta **8080**.

---

## 🚀 Requisito Extra: Cliente Non-Browser

Como a aplicação é uma API RESTful, ela não possui uma interface gráfica nativa (páginas HTML/CSS na raiz). Para demonstrar **como um cliente non-browser chamaria o web service**, utilizamos a ferramenta **Postman** (podendo também ser utilizado o Thunder Client ou Insomnia) para enviar as requisições HTTP e simular a comunicação entre a API e um front-end/mobile.

Abaixo estão as requisições mapeadas e testadas:

### 1. Cadastrar uma Despesa (Ação: CREATE)
Para criar um registro, o cliente envia uma requisição `POST` contendo os dados da despesa no formato JSON no corpo (`Body`) da requisição.

* **Método:** `POST`
* **URL:** `http://localhost:8080/despesas`
* **Body (Raw / JSON):**
  ```json
  {
    "descricao": "Almoço no RU",
    "valor": 15.50,
    "categoria": "Alimentação",
    "mesReferencia": "Junho"
  }
  ```
* **Comportamento Esperado:** A API recebe o JSON, converte em um objeto `Despesa`, gera um ID sequencial automaticamente e retorna o objeto criado com o **Status HTTP 201 (Created)**.

### 2. Listar Despesas (Ação: READ)
Para visualizar as despesas cadastradas, o cliente envia uma requisição `GET` simples.

* **Método:** `GET`
* **URL:** `http://localhost:8080/despesas`
* **Comportamento Esperado:** A API retorna um array JSON com todos os objetos salvos na memória e o **Status HTTP 200 (OK)**.
  *Exemplo de retorno:*
  ```json
  [
    {
      "id": 1,
      "descricao": "Almoço no RU",
      "valor": 15.50,
      "categoria": "Alimentação",
      "mesReferencia": "Junho"
    }
  ]
  ```

### 3. Deletar Despesa (Ação: DELETE)
Para apagar um registro existente, o cliente deve informar o ID da despesa diretamente na URL.

* **Método:** `DELETE`
* **URL:** `http://localhost:8080/despesas/{id}` *(exemplo: `http://localhost:8080/despesas/1`)*
* **Comportamento Esperado:** O sistema procura o ID correspondente. Se deletado com sucesso, retorna o **Status HTTP 204 (No Content)**. Caso o ID não exista, retorna o **Status HTTP 404 (Not Found)**.