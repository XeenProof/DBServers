drop table if exists message;
drop table if exists account;
create table account (
    account_id int primary key serial,
    username varchar(255) unique,
    password varchar(255)
);
create table message (
    message_id int primary key serial,
    posted_by int,
    message_text varchar(255),
    time_posted_epoch bigint,
    foreign key (posted_by) references  account(account_id)
);

insert into account (username, password) values ('testuser1', 'password');
insert into message (posted_by, message_text, time_posted_epoch) values (1,'test message 1',1669947792);

create table IF NOT EXISTS users (
  user_id serial PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  passwordEncrypted VARCHAR(255) NOT NULL
);
create table IF NOT EXISTS todolists (
  list_id serial PRIMARY KEY,
  name_ VARCHAR(255) NOT NULL,
  CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES users(user_id)
);
create table IF NOT EXISTS todoitems (
  item_id serial PRIMARY KEY,
  description_ VARCHAR(255) NOT NULL,
  completed boolean NOT NULL,
  CONSTRAINT list_id FOREIGN KEY (list_id) REFERENCES todolists(list_id),
  CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES users(user_id)
);


