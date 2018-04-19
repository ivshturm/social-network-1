DELETE FROM user_roles;
DELETE FROM comments;
DELETE FROM articles;
DELETE FROM relations;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, last_name, email, password, birthday) VALUES
  ('User', 'Testov', 'user@yandex.ru', '{noop}password', '2000-01-01 00:00:00'),
  ('Test', 'Unitov', 'test@yandex.ru', '{noop}password', '1990-11-11 00:00:00');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001);

INSERT INTO articles (user_id, name, text, date_time) VALUES
  (100000, 'First article', 'It is the best article!', '2018-04-09 10:10:00'),
  (100000, 'Second article', 'Very bad article', '2018-04-08 10:10:00');

INSERT INTO comments (user_id, article_id, text, date_time) VALUES
  (100001, 100002, 'Good article', '2018-04-12 15:45:33'),
  (100000, 100002, 'Like', '2018-04-11 15:45:33');

INSERT INTO relations (follower_id, following_id) VALUES
  (100001, 100000),
  (100000, 100001);
