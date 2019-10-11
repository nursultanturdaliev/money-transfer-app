INSERT INTO users (id, first_name, last_name) VALUES (1, 'Nursultan', 'Turdaliev');
INSERT INTO users (id, first_name, last_name) VALUES (2, 'Nurali', 'Akimov');
INSERT INTO currencies (id, name) VALUES (1,'USD');
INSERT INTO currencies (id, name) VALUES (2,'CAD');
INSERT INTO currencies (id, name) VALUES (3,'KGS');
INSERT INTO currencies (id, name) VALUES (4,'EUR');

insert INTO transactions (id, transaction_id, user_id,currency_id) VALUES(1,'TRANSACTION-ONE',1,1);
insert INTO transactions (id, transaction_id, user_id,currency_id) VALUES(2,'TRANSACTION-TWO',1,2);
insert INTO transactions (id, transaction_id, user_id,currency_id) VALUES(3,'TRANSACTION-THREE',2,3);
insert INTO transactions (id, transaction_id, user_id,currency_id) VALUES(4,'TRANSACTION-FOUR',2,4);