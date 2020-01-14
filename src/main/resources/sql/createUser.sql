create table users(
	username varchar(50) not null primary key,
	password varchar(100) not null,
	enabled boolean not null
);

create unique index ix_auth_username on authorities (username,authority);