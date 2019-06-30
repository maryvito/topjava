DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meal_seq RESTART WITH 1;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, dateTime, description, calories) VALUES
(100000, '2019-06-30 10:00:00.000000', 'завтрак', 600),
(100000, '2019-06-30 14:00:00.000000', 'обед', 700),
(100000, '2019-06-30 19:00:00.000000', 'ужин', 500),
(100000, '2019-07-01 09:00:00.000000', 'завтрак', 300);