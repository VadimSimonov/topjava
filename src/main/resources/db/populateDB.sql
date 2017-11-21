DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
INSERT INTO meals (date_time,description,calories,user_id) VALUES
  ('2017-11-17 10:59:52', 'Обед',500,100000),
  ('2017-11-17 14:13:54', 'Ужин',200,100000);
