CREATE DATABASE BookShop

USE BookShop

CREATE TABLE BOOKS(
id int identity (1,1),
isbn int,
title varchar(100),
price decimal(18,2)
constraint pk_books primary key (id)
)

--TEST INSERTS

insert into books values (2002, 'The Silmarillion', 600)
insert into books values (2225, 'A song of ice and fire', 650)
