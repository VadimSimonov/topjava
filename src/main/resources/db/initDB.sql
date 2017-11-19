DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS table_id_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE SEQUENCE table_id_seq;
CREATE TABLE meals
(
  user_id INTEGER NOT NULL ,
  id INTEGER PRIMARY KEY DEFAULT nextval('table_id_seq'),
  data    VARCHAR,
  description    VARCHAR,
  calories  INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE

);