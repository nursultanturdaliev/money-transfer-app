INSERT INTO `money-transfer-app`.users (id, first_name, last_name) VALUES (1, 'Nursultan', 'Turdaliev');
INSERT INTO `money-transfer-app`.users (id, first_name, last_name) VALUES (2, 'Nurali', 'Akimov');

insert INTO transactions (id, transaction_id, user_id) VALUES(1,'TRANSACTION-ONE',1);
insert INTO transactions (id, transaction_id, user_id) VALUES(2,'TRANSACTION-ONE',1);
insert INTO transactions (id, transaction_id, user_id) VALUES(3,'TRANSACTION-TWO',2);
insert INTO transactions (id, transaction_id, user_id) VALUES(4,'TRANSACTION-TWO',2);