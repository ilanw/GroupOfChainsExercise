CREATE TABLE `Groups` (
  `group_id` int NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (group_id),
  `parent_group_id` int DEFAULT NULL,
  `group_name` varchar(255),
  `group_type` varchar(255)
);
ALTER TABLE Groups AUTO_INCREMENT=100;
INSERT INTO Groups (group_name, group_type)VALUES ('Fox',"Clothes");
INSERT INTO Groups (group_name, group_type)VALUES ('Bezeq',"Network");
INSERT INTO Groups (group_name, group_type)VALUES ('A',"Network");
INSERT INTO Groups (parent_group_id, group_name, group_type)VALUES (1, 'Fox Kids',"Clothes for Kids");


CREATE TABLE `Cities` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (city_id)
);
ALTER TABLE cities AUTO_INCREMENT=100;
INSERT INTO Cities (city_name) VALUES ( 'Tel-Aviv');
INSERT INTO Cities (city_name) VALUES ( 'Acco');
INSERT INTO Cities (city_name) VALUES ( 'Eilat');

CREATE TABLE `Streets` (
  `street_id` int NOT NULL AUTO_INCREMENT,
  `city_id` int NOT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (street_id)
);
ALTER TABLE Streets AUTO_INCREMENT=100;
INSERT INTO Streets (city_id,street_name)VALUES (100, 'Ben-Yehuda');
INSERT INTO Streets (city_id,street_name)VALUES (101, 'Bilu');
INSERT INTO Streets (city_id,street_name)VALUES ( 100, 'Amos');



CREATE TABLE `Addresses` (
  `address_id` int NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (address_id),
  `city_id` varchar(255) DEFAULT NULL,
  `street_id` varchar(255) DEFAULT NULL,
  `building_No` bigint(20) DEFAULT NULL
);
ALTER TABLE Addresses AUTO_INCREMENT=100;
INSERT INTO Addresses (city_id,street_id,building_No)VALUES ( 1, 1,20);
INSERT INTO Addresses (city_id,street_id,building_No)VALUES ( 2, 1, 22);

CREATE TABLE `Stores` (
  `stores_id` int NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (stores_id),
  `store_name` varchar(255) not NULL,
  `address_id` int DEFAULT NULL,
  `mall_id` int DEFAULT NULL,
  `group_id` int NOT null,
  FOREIGN KEY (group_id) REFERENCES Groups(group_id)
);
ALTER TABLE Stores AUTO_INCREMENT=100;
INSERT INTO Stores (store_name,address_id,group_id)VALUES ('Fox Acco',100,  100);
INSERT INTO Stores (store_name,mall_id,group_id)VALUES ( 'Fox Eilat',101, 101);

CREATE TABLE `Employees` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (employee_id),
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `stores_id` int DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  FOREIGN KEY (stores_id) REFERENCES Stores(stores_id),
  FOREIGN KEY (group_id) REFERENCES Groups(group_id)
);
ALTER TABLE Employees AUTO_INCREMENT=100;
INSERT INTO Employees (first_name,last_name,age,stores_id)VALUES ( 'Guy', 'Bitan', 40,100);
INSERT INTO Employees (first_name,last_name,age,group_id)VALUES ( 'Doron', 'Niv', 32,100);
INSERT INTO Employees (first_name,last_name,age,stores_id)VALUES ( 'Ilan', 'Valershtein', 40,101);

CREATE TABLE `Malls` (
  `mall_id` int NOT NULL AUTO_INCREMENT,
  `mall_name` varchar(255) DEFAULT NULL,
  `mall_group_id` int NOT null,
  `address_id` int NOT null,
  PRIMARY KEY (mall_id),
  FOREIGN KEY (address_id) REFERENCES Addresses(address_id)
);
ALTER TABLE Malls AUTO_INCREMENT=100;
INSERT INTO Malls (mall_name,mall_group_id,address_id)VALUES ( 'Azriely',2999,100);
INSERT INTO Malls (mall_name,mall_group_id,address_id)VALUES ( 'G',2998,101);