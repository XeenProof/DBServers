create table IF NOT EXISTS users (
  user_id serial PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  passwordEncrypted VARCHAR(255) NOT NULL
);
create table IF NOT EXISTS todolists (
  list_id serial PRIMARY KEY,
  name_ VARCHAR(255) NOT NULL,
  owner_id INT NOT NULL,
  CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES users(user_id)
);
create table IF NOT EXISTS todoitems (
  item_id serial PRIMARY KEY,
  description_ VARCHAR(255) NOT NULL,
  completed boolean NOT NULL,
  list_id INT NOT NULL,
  owner_id INT NOT NULL,
  CONSTRAINT list_id FOREIGN KEY (list_id) REFERENCES todolists(list_id),
  CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES users(user_id)
);

