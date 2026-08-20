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
.\gradlew.bat bootRun
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
.\gradlew.bat test
```

Os relatórios de execução dos testes serão gerados automaticamente no diretório `build/reports/tests/test/`.

## Padrão de Arquitetura

O projeto utiliza uma **Arquitetura em Camadas** enriquecida com conceitos táticos do **Domain-Driven Design (DDD)**. Como a persistência é implementada manualmente utilizando a JDBC API, essa abordagem isola o coração do sistema das tecnologias externas.

No Domain-Driven Design (DDD), cada conceito serve a um propósito específico na modelagem do software:

- **DTO (Data Transfer Object):** Objetos passivos e imutáveis (geralmente implementados como `records`) usados exclusivamente para transportar dados entre as requisições HTTP da API e o sistema, sem regras de negócio.
- **Value Object (Objeto de Valor):** Objetos imutáveis que descrevem características ou medidas do domínio, sem identidade própria. Possuem validações próprias para garantir que nunca existam em estado inválido (ex: `CPF`, `QuantiaMonetaria`).
- **Entity (Entidade):** Objetos definidos por uma identidade única (um ID) que persiste ao longo do tempo. Encapsulam o estado e o comportamento real das regras de negócio (ex: `Usuario`, `Transacao`).
- **Aggregate (Agregado):** Um grupo de entidades e objetos de valor tratados como uma unidade única de consistência. Possui uma "Raiz" (Aggregate Root) que controla todas as mudanças e garante que as regras do grupo sejam respeitadas (ex: uma `ContaBancaria` gerenciando seus `Saldos` e `Limites`).
- **Repository (Repositório):** Abstração responsável por gerenciar a persistência dos Agregados, permitindo salvá-los e recuperá-los do banco de dados como uma unidade inteira.
- **Domain Service (Serviço de Domínio):** Contém lógica de negócio pura que não se encaixa naturalmente dentro de uma única entidade ou que precisa validar interações entre múltiplos agregados.

### Organização de Pacotes

Os pacotes são organizados internamente (`src/main/java/com/banco_real/api_transacoes/`) da seguinte forma:

- **/controllers:** Portas de entrada da aplicação. São responsáveis por receber as requisições HTTP, converter o payload JSON para DTOs, delegar a execução aos _Services_ e retornar as respostas HTTP adequadas.
- **/services (Application Services):** Orquestradores de casos de uso. Eles buscam os dados necessários via _Repositories_, acionam as regras de negócio nas entidades do domínio e orquestram a persistência do novo estado.
- **/repositories:** Camada de persistência. Concentra a execução de queries SQL diretas no PostgreSQL via JDBC e as interações NoSQL com o MongoDB. São responsáveis por converter os registros do banco em entidades do domínio (idratação) e vice-versa.
- **/models:** O centro da aplicação, blindado de frameworks. Contém as subpastas/pacotes com as Entidades, Agregados, Objetos de Valor e os DTOs do sistema.
