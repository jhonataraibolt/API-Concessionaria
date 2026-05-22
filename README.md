# 🚗 API Concessionária

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

Uma API REST completa e robusta para o gerenciamento de uma concessionária de veículos, desenvolvida usando **Spring Boot**. O sistema foi projetado seguindo as boas práticas de desenvolvimento de software, arquitetura em camadas e manipulação segura de dados.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando a seguinte pilha tecnológica:

- **Java 17**: Linguagem principal do ecossistema.
- **Spring Boot 3.x**: Framework para aceleração e configuração simplificada do projeto.
  - **Spring Web**: Para criação de endpoints RESTful baseados em MVC.
  - **Spring Data JPA**: Abstração de persistência de dados e integração ORM.
  - **Spring Validation**: Validação de dados de entrada usando anotações (`@Valid`, `@NotNull`, etc.).
- **Apache Maven**: Gerenciador de dependências e automação do escopo de build.
- **Banco de Dados**: Suporte nativo a bancos relacionais (configurável via `application.properties`).

---

## 📐 Arquitetura do Sistema

O projeto adota uma estrutura de arquitetura dividida em camadas lógicas para garantir a manutenibilidade, separação de conceitos e facilidade de testes:

src/main/java/projeto/java/API/

├── controller/    -    # Camada de Entrada: Expõe os endpoints REST e valida as requisições.

├── model/         -    # Entidades: Representação das tabelas do banco de dados e objetos de negócio.

├── dto/           -    # Data Transfer Objects: Estruturas customizadas para entrada/saída de dados.

├── repository/    -    # Camada de Acesso a Dados: Interfaces que herdam JpaRepository para operações CRUD.

├── service/       -    # Camada de Negócio: Centraliza as regras de negócio e validações da aplicação.

└── exception/     -    # Tratamento Global: Manipulação centralizada de erros e respostas HTTP personalizadas.

---

## 🛑 Tratamento Global de Exceções

A API possui um mecanismo centralizado de tratamento de erros (`@ControllerAdvice`), garantindo que qualquer falha na requisição retorne um payload limpo, sem expor stack traces do servidor:

- **`400 Bad Request`**: Disparado em casos de JSON malformado ou falha nas anotações de validação (ex: campos obrigatórios vazios). Retorna um detalhamento do campo inválido.
- **`404 Not Found`**: Disparado quando um veículo ou recurso solicitado por ID não existe no banco de dados.
- **`409 Conflict`**: Retornado em tentativas de duplicidade de dados restritos (como chassi ou placa idênticos).

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
Antes de começar, você precisará ter instalado em sua máquina:
- [JDK 17](https://www.oracle.com/java/technologies/downloads/) ou superior.
- [Maven](https://maven.apache.org/) (opcional, se usar o wrapper `./mvnw`).
- Uma ferramenta de testes de API como o **Postman** ou **Insomnia**.

### Passo a Passo

1. **Clonar o Repositório:**
   ```bash
   git clone https://github.com/jhonataraibolt/API-Concessionaria.git
   
Configurar as Variáveis de Ambiente:Abra o arquivo src/main/resources/application.properties e configure as credenciais do seu banco de dados ou ajuste a porta do servidor se necessário:Propertiesspring.application.name=API
server.port=8080

 spring.datasource.url=jdbc:mysql://localhost:5432/concessionaria
 
 spring.datasource.username=seu_usuario
 
 spring.datasource.password=sua_senha

Executar a Aplicação:Você pode rodar diretamente pela sua IDE (como o IntelliJ IDEA executando a classe ApiApplication) ou via terminal:Bash# No Linux/macOS
./mvnw spring-boot:run


📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes. Desenvolvido com ☕ por Jhonata Raibolt.
