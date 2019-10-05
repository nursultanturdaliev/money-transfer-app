create table currencies
(
	id bigint not null
		primary key,
	name varchar(3) null
)
engine=MyISAM;

create table transactions
(
	id bigint auto_increment
		primary key,
	amount int not null,
	transaction_id varchar(255) null,
	currency_id bigint null,
	user_id bigint not null
)
engine=MyISAM;

create index FK812edr8o27pte306gvbmypytx
	on transactions (currency_id);

create index FKqwv7rmvc8va8rep7piikrojds
	on transactions (user_id);

create table users
(
	id bigint auto_increment
		primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null
)
engine=MyISAM;

