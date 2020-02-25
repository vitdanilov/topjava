DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user1@yandex.ru', 'password'),
  ('Admin', 'admi1@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
((select id from users where name='User'), now(),'обед1', 1500);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
((select id from users where name='User'), now(),'омлет1', 1000);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
((select id from users where name='Admin'), now(),'админский омлет1', 1000);


INSERT INTO meals (user_id, date_time, description, calories) VALUES
((select id from users where name='User'), now(), 'Шеф омлет', 1000);