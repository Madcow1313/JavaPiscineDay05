DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.users (
    id SERIAL PRIMARY KEY,
    login varchar(255) NOT NULL unique,
    password varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatrooms(
    id SERIAL PRIMARY KEY ,
    name varchar(255) NOT NULL unique,
    owner integer references chat.users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages(
    id SERIAL PRIMARY KEY ,
    author integer references chat.users(id) NOT NULL,
    room integer references chat.chatrooms(id) NOT NULL,
    text varchar(255) NOT NULL ,
    time timestamp default CURRENT_TIMESTAMP
);

