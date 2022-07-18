CREATE database zssn; use zssn;

CREATE TABLE survivor( id int primary key auto_increment, name varchar(50), age int, gender varchar(2), lat float, lng float, seenCount int, infected boolean DEFAULT false );

CREATE TABLE item( id int primary key auto_increment, name varchar(25), points int );

CREATE TABLE inventory( id int primary key auto_increment, survivor_id int, item_id int, quantity int, constraint fk_survivor_id foreign key (survivor_id) references survivor(id)
ON
DELETE cascade, constraint fk_item_id foreign key (item_id) references item(id)
ON
DELETE cascade );

SELECT  *
FROM inventory inv
INNER JOIN item it
ON it.id = inv.item_id
WHERE inv.survivor_id IN (1, 3);

SELECT  COUNT(*) AS survivors
FROM survivor
WHERE infected = false
UNION
SELECT  COUNT(*) AS nonsurvivors
FROM survivor
WHERE infected = true;

SELECT  *
FROM inventory
INNER JOIN survivor
ON inventory.survivor_id = survivor.id
WHERE inventory.item_id = 1
AND survivor.infected = 0
UNION
SELECT  *
FROM inventory
INNER JOIN survivor
ON inventory.survivor_id = survivor.id
WHERE inventory.item_id = 2
AND survivor.infected = 0
UNION
SELECT  *
FROM inventory
INNER JOIN survivor
ON inventory.survivor_id = survivor.id
WHERE inventory.item_id = 3
AND survivor.infected = 0
UNION
SELECT  *
FROM inventory
INNER JOIN survivor
ON inventory.survivor_id = survivor.id
WHERE inventory.item_id = 4
AND survivor.infected = 0;

SELECT  SUM(iv.quantity) AS quantity
       ,SUM(it.points)   AS points
FROM inventory iv
INNER JOIN item it
ON it.id = iv.item_id
INNER JOIN survivor sur
ON sur.id = iv.survivor_id
WHERE sur.infected = 1;