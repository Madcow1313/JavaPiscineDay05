insert into chat.users(login, password) values
('Yagami Light', 'Death Note'), ('Naruto Uzumaki', 'Naruto'),
('Komi Shouko', '...'), ('Kaguya Shinomiya', 'Love and war'),
('Ichigo Kurosaki', 'Bleach');

insert into chat.chatrooms(name, owner) values
('Tokio', (select id from chat.users where login = 'Yagami Light')),
('Konoha', (select id from chat.users where login = 'Naruto Uzumaki')),
('Homeroom', (select id from chat.users where login = 'Komi Shouko')),
('Shuchiin', (select id from chat.users where login = 'Kaguya Shinomiya')),
('Soul Society', (select id from chat.users where login = 'Ichigo Kurosaki'));

insert into chat.messages (author, room, text) values
((select id from chat.users where login = 'Yagami Light'), (select id from chat.chatrooms where name = 'Tokio'), 'All according to keikaku'),
((select id from chat.users where login = 'Naruto Uzumaki'), (select id from chat.chatrooms where name = 'Konoha'), 'I will become Hokage'),
((select id from chat.users where login = 'Komi Shouko'), (select id from chat.chatrooms where name = 'Homeroom'), '...'),
((select id from chat.users where login = 'Kaguya Shinomiya'), (select id from chat.chatrooms where name = 'Shuchiin'), 'How cute'),
((select id from chat.users where login = 'Ichigo Kurosaki'), (select id from chat.chatrooms where name = 'Soul Society'), 'BANKAI!!!!!');