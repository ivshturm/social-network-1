DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS relations;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  last_name        VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  birthday         TIMESTAMP               NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

-- CREATE TABLE articles (
--   id          INTEGER   PRIMARY KEY DEFAULT nextval('global_seq'),
--   user_id     INTEGER   NOT NULL,
--   name        TEXT      NOT NULL,
--   text        TEXT      NOT NULL,
--   date_time   TIMESTAMP DEFAULT now(),
--   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );

CREATE TABLE relations
(
  id              INTEGER   PRIMARY KEY DEFAULT nextval('global_seq'),
  follower_id     INTEGER   NOT NULL REFERENCES users (id) ON DELETE CASCADE,
  following_id    INTEGER   NOT NULL REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX relations_unique_idx ON relations (follower_id, following_id);

-- CREATE TABLE comments (
--   id          INTEGER   PRIMARY KEY DEFAULT nextval('global_seq'),
--   user_id     INTEGER   NOT NULL,
--   article_id  INTEGER   NOT NULL,
--   text        TEXT      NOT NULL,
--   date_time   TIMESTAMP DEFAULT now(),
--   FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
--   FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE
-- );