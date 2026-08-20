CREATE TABLE "Login" (
  "id" integer PRIMARY KEY,
  "email" varchar UNIQUE,
  "senha_hash" varchar,
  "tipo_usuario" varchar
);

CREATE TABLE "Cliente" (
  "id" integer PRIMARY KEY,
  "id_login" integer UNIQUE,
  "nome" varchar,
  "cpf" varchar UNIQUE,
  "telefone" varchar,
  "endereco" varchar,
  "biometria" blob,
  "documento" blob,
  "selfie" blob,
  "data_cadastro" timestamp
);

CREATE TABLE "Cargo" (
  "id" integer PRIMARY KEY,
  "nome" varchar,
  "descricao" varchar
);

CREATE TABLE "Funcionario" (
  "id" integer PRIMARY KEY,
  "id_login" integer UNIQUE NOT NULL,
  "id_cargo" integer NOT NULL,
  "nome" varchar,
  "matricula" varchar UNIQUE,
  "telefone" varchar,
  "endereco" varchar,
  "data_cadastro" timestamp
);

CREATE TABLE "Conta_Banco" (
  "id" integer PRIMARY KEY,
  "id_cliente" integer UNIQUE,
  "agencia" varchar,
  "numero" varchar UNIQUE,
  "saldo" decimal,
  "limite_disponivel" decimal,
  "status" varchar
);

CREATE TABLE "Cartao" (
  "id" integer PRIMARY KEY,
  "id_conta" integer NOT NULL,
  "numero" varchar UNIQUE,
  "cvv_hash" varchar,
  "tipo" varchar,
  "validade" varchar,
  "limite_disponivel" decimal,
  "limite_usado" decimal,
  "status" varchar
);

CREATE TABLE "Fatura" (
  "id" integer PRIMARY KEY,
  "id_cartao" integer NOT NULL,
  "mes_ano" varchar,
  "vencimento" date,
  "valor" decimal,
  "status" varchar
);

CREATE TABLE "Transacao" (
  "id" integer PRIMARY KEY,
  "id_conta_origem" integer,
  "id_conta_destino" integer,
  "id_fatura" integer,
  "codigo" varchar UNIQUE,
  "tipo" varchar,
  "meio" varchar,
  "valor" decimal,
  "status" varchar,
  "data_hora" timestamp
);

ALTER TABLE "Login" ADD FOREIGN KEY ("id") REFERENCES "Cliente" ("id_login") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Login" ADD FOREIGN KEY ("id") REFERENCES "Funcionario" ("id_login") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Funcionario" ADD FOREIGN KEY ("id_cargo") REFERENCES "Cargo" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Cliente" ADD FOREIGN KEY ("id") REFERENCES "Conta_Banco" ("id_cliente") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Cartao" ADD FOREIGN KEY ("id_conta") REFERENCES "Conta_Banco" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Fatura" ADD FOREIGN KEY ("id_cartao") REFERENCES "Cartao" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Transacao" ADD FOREIGN KEY ("id_conta_origem") REFERENCES "Conta_Banco" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Transacao" ADD FOREIGN KEY ("id_conta_destino") REFERENCES "Conta_Banco" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "Transacao" ADD FOREIGN KEY ("id_fatura") REFERENCES "Fatura" ("id") DEFERRABLE INITIALLY IMMEDIATE;
