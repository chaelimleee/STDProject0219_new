create table users (
	userid          varchar2(50)	primary key, 
	username		varchar2(50)	not null,
	userpassword	varchar2(50)	not null,
	userage			number(3)	    not null,
	useremail		varchar2(50)	not null
);

insert into users values ('abcd1234', '홍길동', '123456789', 20, 'abcd@abcd.com');

select * from users;

update users set userpassword='java1234', useremail='java@abcd.com'
where userid='abcd1234';

delete from users where userid='abcd1234';