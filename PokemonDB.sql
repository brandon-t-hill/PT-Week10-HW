create database if not exists Pokemon;

use Pokemon;
drop table if exists Pokemon;

create table Pokemon(
    id int not null AUTO_INCREMENT,
    name varchar(32) not null,
    type1 int not null,
    type2 int,
    primary key(id)
);

INSERT INTO `pokemon` VALUES(1, "Bulbasaur", 4, 8);
INSERT INTO `pokemon` VALUES(2, "Ivysaur", 4, 8);
INSERT INTO `pokemon` VALUES(3, "Venusaur", 4, 8);
INSERT INTO `pokemon`(id, name, type1) VALUES(4, "Charmander", 2);
INSERT INTO `pokemon`(id, name, type1) VALUES(5, "Charmeleon" , 2);
INSERT INTO `pokemon` VALUES(6, "Charizard", 2, 10);
INSERT INTO `pokemon`(id, name, type1) VALUES(7, "Squirtle", 3);
INSERT INTO `pokemon`(id, name, type1) VALUES(8, "Wartortle", 3);
INSERT INTO `pokemon`(id, name, type1) VALUES(9, "Blastoise", 3);
