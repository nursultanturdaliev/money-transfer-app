insert into users (id, first_name, last_name, username, email, password) values (1, 'Nursultan', 'Turdaliev', 'nursultanturdaliev', 'nursultanturdaliev@gmail.com','{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG');
insert into users (id, first_name, last_name, username, email, password) values (2, 'Nurali', 'Akimov', 'nurali.akimov', 'nurali.akimov@gmail.com','{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG');
insert into currencies (id, name) values (1,'USD');
insert into currencies (id, name) values (2,'CAD');
insert into currencies (id, name) values (3,'KGS');
insert into currencies (id, name) values (4,'EUR');

insert into transactions (id, transaction_id, user_id,currency_id) values(1,'TRANSACTION-ONE',1,1);
insert into transactions (id, transaction_id, user_id,currency_id) values(2,'TRANSACTION-TWO',1,2);
insert into transactions (id, transaction_id, user_id,currency_id) values(3,'TRANSACTION-THREE',2,3);
insert into transactions (id, transaction_id, user_id,currency_id) values(4,'TRANSACTION-FOUR',2,4);


insert into roles(id, name)  values(1, 'ROLE_USER');