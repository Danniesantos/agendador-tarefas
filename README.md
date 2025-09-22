# Agendador de Tarefas

API para gerenciamento de tarefas, desenvolvida como projeto pessoal/acadêmico em **Java com Spring Boot**.

---

## 🚀 Tecnologias Utilizadas
- **Linguagem:** Java  
- **Frameworks:** Spring Boot, Spring Data JPA, OpenFeign  
- **Banco de Dados:** MongoDB  
- **Documentação:** Swagger  
- **Testes:** JUnit  
- **DevOps:** Docker, Git/GitHub  
- **Qualidade de Código:** SonarLint  

---

## 📂 Funcionalidades
- CRUD de tarefas (Criar, Listar, Atualizar, Excluir)  
- Integração com microserviços via **OpenFeign**  
- Documentação da API com **Swagger**  
- Testes unitários para principais endpoints  
- Persistência de dados em **MongoDB/PostgreSQL**  

---

## 🛠️ Como Rodar o Projeto
1. Clone o repositório:
```bash
git clone https://github.com/Danniesantos/agendador-tarefas.git

2. Entre na pasta do projeto:
cd agendador-tarefas

3. Configure o banco de dados (MongoDB ou PostgreSQL) e atualize o application.properties se necessário.
Rode o projeto usando Maven ou Gradle:

4. mvn spring-boot:run
ou
./gradlew bootRun

5. Acesse a documentação da API:
http://localhost:8080/swagger-ui.html
