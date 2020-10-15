CREATE TABLE "users" (
  "id" BIGSERIAL PRIMARY KEY,
  "first_name" varchar(100),
  "second_name" varchar(100),
  "age" int,
  "created_at" timestamp,
  "phone" varchar(15),
  "email" varchar(200),
  "is_verified" boolean,
  "is_active" boolean
);

CREATE TABLE "user_assets" (
  "id" bigint PRIMARY KEY,
  "institution_id" bigint,
  "asset_type_id" int,
  "asset_instrument_id" int,
  "user_id" bigint,
  "nick_name" varchar(30),
  "holder_name" varchar(30),
  "amount" numeric(20, 2),
  "maturity_date" timestamp ,
  "returns" numeric(6,3),
  "created_at" timestamp DEFAULT (now()),
  "modified_at" timestamp DEFAULT (now())
);

CREATE TABLE "asset_type" (
  "id" int PRIMARY KEY,
  "type_name" varchar(100)
);

CREATE TABLE "asset_instrument" (
  "id" int PRIMARY KEY,
  "asset_type_id" int,
  "instrument_name" varchar(100),
  "default_returns" numeric
);

CREATE TABLE "institution" (
  "id" int PRIMARY KEY,
  "name" varchar(100),
  "indexed_name" varchar(20),
  "type" varchar(20),
  "tags" text[] not null default '{}'
);

ALTER TABLE "user_assets" ADD FOREIGN KEY ("institution_id") REFERENCES "institution" ("id");

ALTER TABLE "asset_instrument" ADD FOREIGN KEY ("asset_type_id") REFERENCES "asset_type" ("id");

ALTER TABLE "user_assets" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "user_assets" ADD FOREIGN KEY ("asset_type_id") REFERENCES "asset_type" ("id");

ALTER TABLE "user_assets" ADD FOREIGN KEY ("asset_instrument_id") REFERENCES "asset_instrument" ("id");

create index tags_index on institution using gin (tags);