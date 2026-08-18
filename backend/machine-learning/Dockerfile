# ==========================================
# ESTÁGIO 1: Builder (Construção e Compilação)
# ==========================================
FROM python:3.12-slim AS builder

# Variáveis de ambiente essenciais para o Python no Docker
ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1

WORKDIR /app

# Instala ferramentas do sistema operacional (C/C++) necessárias
# para compilar bibliotecas pesadas de Machine Learning (como NumPy e Pandas)
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential \
    gcc \
    && rm -rf /var/lib/apt/lists/*

# Cria um Ambiente Virtual (venv) dentro do contêiner
RUN python -m venv /opt/venv
# Garante que os próximos comandos usem esse venv
ENV PATH="/opt/venv/bin:$PATH"

# Copia e instala as dependências
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# ==========================================
# ESTÁGIO 2: Runner (Produção)
# ==========================================
FROM python:3.12-slim AS runner

# Repete as variáveis para o ambiente final
ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    PATH="/opt/venv/bin:$PATH"

WORKDIR /app

# Cria um usuário não-root
RUN useradd -m -s /bin/bash appuser

# Copia apenas o "venv" pronto do estágio de build
COPY --from=builder /opt/venv /opt/venv

# Copia o código-fonte do motor de IA
COPY --chown=appuser:appuser . .

# Assume a identidade do usuário restrito por segurança
USER appuser

# Expõe a porta que a sua API Python (ex: Flask ou FastAPI) vai escutar
EXPOSE 5000

# O comando que o servidor executará.
# (Substitua "app.py" pelo nome do seu arquivo principal de inicialização)
CMD ["python", "app.py"]
