CREATE database zssn; use zssn;

CREATE TABLE survivor( id int primary key auto_increment, name varchar(50), age int, gender varchar(2), lat float, lng float, seenCount int, infected boolean DEFAULT false );

CREATE TABLE item( id int primary key auto_increment, name varchar(25), points int );

CREATE TABLE inventory( id int primary key auto_increment, survivor_id int, item_id int, quantity int, constraint fk_survivor_id foreign key (survivor_id) references survivor(id)
ON
DELETE cascade, constraint fk_item_id foreign key (item_id) references item(id)
ON
DELETE cascade );

insert into item values ("Water",4),
("Food",3),
("Medication",2),
("Ammunition",1);