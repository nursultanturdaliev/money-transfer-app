INSERT INTO users (id, `created_at`, `email`, `first_name`, `last_name`, `password`, `updated_at`, is_active) VALUES (1, '2019-10-24 17:55:56', 'nursultan@peaksoft.us', 'Nursultan', 'Turdaliev', '$2a$10$ZuhGJnhIGZJ6dmVwPAJJiu1qQOARFImW8cdGzK9UngDedhG8d1zSm', '2019-10-24 17:55:56', true);
insert into users (id, first_name, last_name, email, password) values (2, 'Nurali', 'Akimov', 'nurali.akimov@gmail.com','{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG');
insert into currencies (id, name) values (1,'USD');
insert into currencies (id, name) values (2,'CAD');
insert into currencies (id, name) values (3,'KGS');
insert into currencies (id, name) values (4,'EUR');

insert into receivers(id, first_name, last_name, phone_number) values ( 1, 'Elon', 'Musk', '7742321321' );
insert into receivers(id, first_name, last_name, phone_number) values ( 2, 'Almazbek', 'Atambaev', '7743211321' );
insert into receivers(id, first_name, last_name, phone_number) values ( 3, 'Jack', 'Ma', '7742332121' );


insert into transactions (id, transaction_id, currency_id, receiver_id, amount, phone_number) values(1,'TRANSACTION-ONE',1,1,1000, '7742321321');
insert into transactions (id, transaction_id, currency_id, receiver_id, amount, phone_number) values(2,'TRANSACTION-TWO',1,1, 10, '7742321321');
insert into transactions (id, transaction_id, currency_id, receiver_id, amount, phone_number) values(3,'TRANSACTION-THREE',2,1,2000, '7742321321');
insert into transactions (id, transaction_id, currency_id, receiver_id, amount, phone_number) values(4,'TRANSACTION-FOUR',2,1,2000, '7742321321');



insert into roles(id, name)  values(1, 'ROLE_USER');

insert into users_roles (users_id, roles_id) values(1,1);