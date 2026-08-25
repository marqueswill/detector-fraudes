CREATE TABLE "logins" (
  "id" integer PRIMARY KEY,
  "email_hash" varchar UNIQUE,
  "password_hash" varchar,
  "user_type" varchar
);

CREATE TABLE "customers" (
  "id" integer PRIMARY KEY,
  "login_id" integer UNIQUE,
  "full_name" varchar,
  "national_id" varchar UNIQUE,
  "phone" varchar,
  "address" varchar,
  "biometrics" blob,
  "document_image" blob,
  "selfie" blob,
  "created_at" timestamp
);

CREATE TABLE "roles" (
  "id" integer PRIMARY KEY,
  "name" varchar,
  "description" varchar
);

CREATE TABLE "employees" (
  "id" integer PRIMARY KEY,
  "login_id" integer UNIQUE NOT NULL,
  "role_id" integer NOT NULL,
  "full_name" varchar,
  "registration_number" varchar UNIQUE,
  "phone" varchar,
  "address" varchar,
  "created_at" timestamp
);

CREATE TABLE "bank_accounts" (
  "id" integer PRIMARY KEY,
  "customer_id" integer UNIQUE,
  "branch_code" varchar,
  "account_number" varchar UNIQUE,
  "balance" decimal,
  "available_limit" decimal,
  "status" varchar
);

CREATE TABLE "cards" (
  "id" integer PRIMARY KEY,
  "account_id" integer NOT NULL,
  "card_number" varchar UNIQUE,
  "cvv_hash" varchar,
  "type" varchar,
  "expiration_date" varchar,
  "available_limit" decimal,
  "used_limit" decimal,
  "status" varchar
);

CREATE TABLE "invoices" (
  "id" integer PRIMARY KEY,
  "card_id" integer NOT NULL,
  "month_year" varchar,
  "due_date" date,
  "total_amount" decimal,
  "status" varchar
);

CREATE TABLE "transactions" (
  "id" integer PRIMARY KEY,
  "source_account_id" integer,
  "destination_account_id" integer,
  "invoice_id" integer,
  "transaction_code" varchar UNIQUE,
  "type" varchar,
  "payment_method" varchar,
  "amount" decimal,
  "status" varchar,
  "created_at" timestamp
);

ALTER TABLE "logins" ADD FOREIGN KEY ("id") REFERENCES "customers" ("login_id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "logins" ADD FOREIGN KEY ("id") REFERENCES "employees" ("login_id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "employees" ADD FOREIGN KEY ("role_id") REFERENCES "roles" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "customers" ADD FOREIGN KEY ("id") REFERENCES "bank_accounts" ("customer_id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "cards" ADD FOREIGN KEY ("account_id") REFERENCES "bank_accounts" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "invoices" ADD FOREIGN KEY ("card_id") REFERENCES "cards" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "transactions" ADD FOREIGN KEY ("source_account_id") REFERENCES "bank_accounts" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "transactions" ADD FOREIGN KEY ("destination_account_id") REFERENCES "bank_accounts" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "transactions" ADD FOREIGN KEY ("invoice_id") REFERENCES "invoices" ("id") DEFERRABLE INITIALLY IMMEDIATE;
