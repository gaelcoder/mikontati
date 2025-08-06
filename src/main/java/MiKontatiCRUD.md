# MiKontati - Agenda de Contatos em Java

> Uma aplicação de console simples para gerenciamento de contatos (CRUD), desenvolvida em Java com persistência de dados em um banco de dados MySQL. Puramente com o objetivo de reforçar as bases de Java e SQL.

Este projeto é uma demonstração de uma aplicação de linha de comando (CLI) que implementa as operações básicas de um cadastro: Criar, Ler, Atualizar e Excluir (CRUD). A arquitetura separa as responsabilidades em uma classe de modelo (`Contato`), uma camada de acesso a dados (`ContatoDAO`) e a classe principal que gerencia a interação com o usuário (`MiKontati`).

## ✨ Funcionalidades

*   **Adicionar Contatos**: Crie e salve novos contatos no banco de dados.
*   **Listar Contatos**: Visualize todos os contatos salvos (funcionalidade a ser implementada).
*   **Atualizar Contatos**: Modifique informações de um contato existente através de seu ID.
*   **Excluir Contatos**: Remova um contato do banco de dados (funcionalidade a ser implementada).
*   **Interface de Console**: Interação com o usuário através de um menu de texto simples.

## 🛠️ Tecnologias Utilizadas

*   **Java 21**: Linguagem de programação principal.
*   **JDBC (Java Database Connectivity)**: API padrão para a conexão com o banco de dados.
*   **MySQL**: Sistema de Gerenciamento de Banco de Dados para persistir os dados.
*   **Maven**: Ferramenta para gerenciamento de dependências e build do projeto.

## 📋 Pré-requisitos

Antes de começar, certifique-se de que você tem os seguintes softwares instalados em sua máquina:
*   [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#jdk21) ou superior.
*   [Apache Maven](https://maven.apache.org/download.cgi).
*   Um servidor [MySQL](https://dev.mysql.com/downloads/mysql/) ativo.

## ⚙️ Configuração e Instalação

Siga os passos abaixo para configurar e executar o projeto localmente.

**1. Clone o Repositório**
```bash
git clone <URL_DO_SEU_REPOSITORIO>
cd MiKontati
```


**2. Configure o Banco de Dados**

Execute o seguinte script SQL no seu cliente MySQL para criar o banco de dados e a tabela necessários.

```bash
CREATE DATABASE IF NOT EXISTS mikontati;
USE mikontati;

CREATE TABLE IF NOT EXISTS contatos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(100)
);
```

**3. Configure a Conexão**

Abra o arquivo e altere as credenciais de acesso (`URL`,`USUARIO`,`SENHA`) para corresponderem à sua configuração local do MySQL. `src/main/java/MySQLConnection.java`

```bash
// Dentro de MySQLConnection.java
public class MySQLConnection {
    // Altere esses valores conforme sua configuração
    private static final String URL = "jdbc:mysql://localhost:3306/mikontati";
    private static final String USUARIO = "seu_usuario";
    private static final String SENHA = "sua_senha";

    // ... resto do código
}
```


**4. Compile o Projeto**

Use o Maven para baixar as dependências e compilar o código-fonte.

```bash
mvn clean install
```

**Como Executar**

Após a compilação, você pode executar a aplicação diretamente pela sua IDE (procurando e executando o método `main` na classe ) ou via linha de comando. `MiKontati`

**Executando via Maven**
```bash
mvn exec:java -Dexec.mainClass="MiKontati"
```