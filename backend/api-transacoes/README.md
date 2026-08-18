# API Transações

O coração do sistema, desenvolvido em Java. É responsável por gerenciar regras de negócio, autenticação, processamento de transferências e persistência de dados, utilizando PostgreSQL para dados relacionais e MongoDB para logs e registros brutos.

## Dependências

Como o foco é aprendizado, só usei coisas básicas porque vou implementar quase tudo na mão:

- Spring Web
- Spring Boot DevTools
- JDBC API
- PostgreSQL Driver
- MongoDB

## Pré-requisitos

- Java Development Kit (JDK) 17 ou superior.
- Docker e Docker Compose (para orquestração dos bancos de dados).

## Configuração do Ambiente

1. Inicie os bancos de dados (PostgreSQL e MongoDB) utilizando o arquivo `docker-compose.yml` localizado na raiz do projeto:

   ```bash
   docker-compose up -d postgres-db mongo-db
   ```

2. Configure as variáveis de ambiente ou o arquivo `src/main/resources/application.properties` da API para conectar aos bancos de dados de acordo com o `docker-compose`. Exemplo:

```properties
# PostgreSQL Relacional
spring.datasource.url=jdbc:postgresql://localhost:5432/banco_db
spring.datasource.username=admin
spring.datasource.password=admin_password
spring.datasource.driver-class-name=org.postgresql.Driver

# MongoDB NoSQL
spring.data.mongodb.uri=mongodb://localhost:27017/logs_db
```

## Running (Executando a aplicação)

O projeto utiliza o wrapper do Gradle (`gradlew`). Para iniciar o servidor de desenvolvimento, navegue até o diretório `backend/api-transacoes` e execute o comando correspondente ao seu sistema operacional:

**Linux / macOS:**

```bash
./gradlew bootRun
```

**Windows:**

```cmd
gradlew.bat bootRun
```

Por padrão, a aplicação iniciará na porta `8080`. O Spring Boot DevTools permite o recarregamento automático da aplicação ao detectar alterações no código compilado.

## Testes

Para executar a suíte de testes unitários e de integração, navegue até o diretório `backend/api-transacoes` e utilize o Gradle:

**Linux / macOS:**

```bash
./gradlew test
```

**Windows:**

```cmd
gradlew.bat test
```

Os relatórios de execução dos testes serão gerados automaticamente no diretório `build/reports/tests/test/`.

## Padrão de Arquitetura

Como a persistência será implementada manualmente utilizando a JDBC API, os pacotes são organizdos internamente (dentro de `src/main/java/com/banco_real/api_transacoes/`) seguindo o padrão de camadas:

- `/controllers`: Responsáveis por receber requisições HTTP e retornar respostas.
- `/services`: Contém as regras de negócio e integrações entre domínios.
- `/repositories`: Classes focadas em executar queries SQL diretas no PostgreSQL via JDBC e interações de documentos via driver do MongoDB.
- `/models`: Entidades de domínio, DTOs e records.
