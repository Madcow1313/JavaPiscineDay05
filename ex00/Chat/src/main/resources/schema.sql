CREATE SCHEMA chat;

CREATE TABLE chat.users (
    UserId SERIAL PRIMARY KEY,
    Login varchar(255) NOT NULL,
    Password varchar(255) NOT NULL
);

CREATE TABLE chat.messages(
    MessageId SERIAL PRIMARY KEY ,
    Author varchar(255),
    Text varchar(255),
    dateTime timestamp default CURRENT_TIMESTAMP
);

CREATE TABLE chat.chatrooms(
    ChatroomId SERIAL PRIMARY KEY ,
    Name varchar(255),
    Owner varchar(255)
);
