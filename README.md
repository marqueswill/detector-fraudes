# Sistema Bancário Real

Este repositório contém a arquitetura completa do sistema de detecção de fraudes em tempo real, composto por microserviços de backend (API de Transações e Serviço de Machine Learning) e interfaces de frontend (Aplicativo do Cliente e Painel do Gestor).

---

## 1. Arquitetura do Sistema

1. **API de Transações (`backend/api-transacoes`)**
   - **Tecnologias:** Java 21, Spring Boot, Gradle.
   - **Responsabilidade:** Receber, validar, persistir e gerenciar o ciclo de vida de transações financeiras. Envia cargas para análise de risco no serviço de ML.

2. **Serviço de Machine Learning (`backend/machine-learning`)**
   - **Tecnologias:** Python 3.11, FastAPI/Flask, Scikit-Learn, Pandas.
   - **Responsabilidade:** Avaliar a probabilidade de fraude de cada transação com base em padrões de comportamento e modelos preditivos treinados.

3. **Aplicativo do Cliente (`frontend/client-app`)**
   - **Tecnologias:** React Native, Expo, TypeScript.
   - **Responsabilidade:** Interface móvel para os clientes realizarem transações, visualizarem extratos e receberem alertas de segurança.

4. **Painel do Gestor (`frontend/manager-app`)**
   - **Tecnologias:** Next.js 15, React 19, TypeScript, Tailwind CSS.
   - **Responsabilidade:** Interface web para analistas de fraude e gestores monitorarem transações suspeitas, métricas do modelo e realizarem auditorias.

---

## 2. Estrutura de Diretórios

```text
detector-fraudes/
├── backend/
│   ├── api-transacoes/        # Microserviço Spring Boot (Java)
│   │   ├── Dockerfile
│   │   ├── build.gradle.kts
│   │   └── src/
│   └── machine-learning/      # Microserviço ML (Python)
│       ├── Dockerfile
│       └── requirements.txt
├── frontend/
│   ├── client-app/            # App Mobile (Expo / React Native)
│   └── manager-app/           # Dashboard Web (Next.js)
├── docker-compose.yml         # Orquestração do ambiente local
└── .env.example               # Variáveis de ambiente padrão

```

---

## 3. Configuração do Ambiente

### Pré-requisitos

- Docker Engine >= 24.0
- Docker Compose >= 2.20
- Node.js >= 20.x (para desenvolvimento frontend local)
- JDK 17 (para desenvolvimento backend local)
- Python 3.11+ (para desenvolvimento ML local)

### Variáveis de Ambiente

Copie o arquivo de exemplo e ajuste os parâmetros necessários:

```bash
cp .env.example .env
```

Conteúdo de referência do `.env`:

```ini
DB_HOST=localhost
DB_PORT=5432
DB_NAME=detector_fraudes
DB_USER=postgres
DB_PASSWORD=postgres

ML_SERVICE_URL=http://localhost:5000/predict
API_TRANSACOES_URL=http://localhost:8080

```

---

## 4. Execução via Docker Compose

Para subir todos os serviços simultaneamente:

```bash
docker-compose up --build -d
```

### Verificação de Status dos Conteineres:

```bash
docker-compose ps
```

### Logs em Tempo Real:

```bash
docker-compose logs -f [nome-do-servico]
```

---

## 5. Instruções para Desenvolvimento Local

### 5.1. Backend - API de Transações (Java / Spring Boot)

Navegue até o diretório da API:

```bash
cd backend/api-transacoes
```

Executar testes unitários e de integração:

```bash
./gradlew test
```

Executar a aplicação em modo de desenvolvimento:

```bash
./gradlew bootRun
```

### 5.2. Backend - Serviço de Machine Learning (Python)

Navegue até o diretório do serviço ML:

```bash
cd backend/machine-learning
```

Criar e ativar o ambiente virtual:

```bash
python -m venv venv
source venv/bin/activate
```

Instalar dependências:

```bash
pip install -r requirements.txt
```

Executar o servidor:

```bash
python main.py
```

### 5.3. Frontend - Aplicativo Cliente (React Native / Expo)

Navegue até o diretório do app móvel:

```bash
cd frontend/client-app
npm install
npx expo start
```

### 5.4. Frontend - Painel do Gestor (Next.js)

Navegue até o diretório da aplicação web:

```bash
cd frontend/manager-app
npm install
npm run dev
```

O painel estará acessível em `http://localhost:3000`.
