# Projeto Java - Conexão com Supabase

Este projeto demonstra como conectar uma aplicação Java a um banco de dados PostgreSQL hospedado no **Supabase**. A aplicação insere um novo registro na tabela `clientes` e, em seguida, exibe todos os registros existentes no console.

![Demonstração do programa em execução](images/img1.png)

## ✨ Tecnologias Utilizadas

-   **Java**
-   **PostgreSQL JDBC Driver**
-   **Supabase**

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para configurar e executar o projeto localmente.

### 1. Pré-requisitos

-   **Java Development Kit (JDK)** instalado. Verifique a versão com o comando `java -version`.
-   **Driver JDBC do PostgreSQL**:
    1.  Baixe o arquivo `.jar` do driver na [página oficial do PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/).
    2.  Coloque o arquivo baixado (ex: `postgresql-42.7.5.jar`) dentro da pasta `lib` na raiz do projeto.

### 2. Configuração das Credenciais

1.  Abra o arquivo `src/App.java`.
2.  Localize e altere as seguintes variáveis com as suas credenciais do Supabase:

    ```java
    String url = "jdbc:postgresql://[SEU_HOST]:6543/postgres";
    String user = "postgres.[SEU_ID_DE_PROJETO]";
    String password = "[SUA_SENHA_AQUI]";
    ```

    > ⚠️ **Atenção:** Nunca salve senhas ou chaves secretas diretamente em repositórios Git públicos. Para projetos reais, considere o uso de variáveis de ambiente.

### 3. Compilação e Execução

Execute os seguintes comandos no terminal, a partir da **pasta raiz do projeto**.

1.  **Compilar o código:**
    Este comando compila o `App.java` e salva o arquivo `.class` resultante na pasta `bin`.

    ```bash
    javac -cp "lib/postgresql-42.7.5.jar" -d bin src/App.java
    ```

2.  **Executar a aplicação:**
    Este comando executa o programa, utilizando as classes da pasta `bin` e o driver da pasta `lib`.

    ```bash
    java -cp "bin:lib/postgresql-42.7.5.jar" App
    ```
    > **Nota:** Se você estiver no Windows, use ponto e vírgula (`;`) em vez de dois pontos (`:`) para separar os caminhos no classpath: `java -cp "bin;lib/postgresql-42.7.5.jar" App`

## ⚙️ Funcionalidades

O script `App.java` executa as seguintes ações em ordem:
1.  **Conexão:** Estabelece uma conexão segura com o banco de dados PostgreSQL usando as credenciais fornecidas.
2.  **Inserção:** Executa um comando `INSERT` para adicionar um novo cliente estático ("Maria Souza") à tabela `clientes`.
3.  **Consulta:** Realiza um `SELECT * FROM clientes` para buscar todos os registros da tabela.
4.  **Exibição:** Itera sobre os resultados da consulta e os exibe formatados no console.
5.  **Fechamento:** A conexão com o banco de dados é fechada automaticamente ao final da execução.