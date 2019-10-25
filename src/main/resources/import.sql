INSERT INTO users (id, `created_at`, `email`, `first_name`, `last_name`, `password`, `updated_at`, `username`) VALUES (1, '2019-10-24 17:55:56', 'turdaliev@gmail.com', 'Nursultan', 'Turdaliev', '$2a$10$ZuhGJnhIGZJ6dmVwPAJJiu1qQOARFImW8cdGzK9UngDedhG8d1zSm', '2019-10-24 17:55:56', 'nursultanturdaliev');
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

insert into users_roles (users_id, roles_id) values(1,1);