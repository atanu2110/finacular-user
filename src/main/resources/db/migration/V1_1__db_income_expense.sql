CREATE TABLE "user_expenses" (
  "id" SERIAL PRIMARY KEY ,
  "user_id" bigint,
  "category" varchar(15),
  "name" varchar(20),
  "amount" numeric(10, 2),
  "period" varchar(10),
  "created_at" timestamp DEFAULT (now()),
  "modified_at" timestamp DEFAULT (now())
);

CREATE TABLE "user_income" (
  "id" SERIAL PRIMARY KEY,
  "user_id" bigint,
  "category" varchar(15),
  "name" varchar(20),
  "amount" numeric(10, 2),
  "period" varchar(10),
  "created_at" timestamp DEFAULT (now()),
  "modified_at" timestamp DEFAULT (now())
);

ALTER TABLE "user_expenses" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
ALTER TABLE "user_income" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
