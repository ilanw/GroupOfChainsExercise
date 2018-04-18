SELECT * FROM test.malls;



CREATE TABLE `Addresses` (
  `address_id` int NOT NULL,
   PRIMARY KEY (address_id),
  `city_id` varchar(255) DEFAULT NULL,
  `street_id` varchar(255) DEFAULT NULL,
  `zip_code` bigint(20) DEFAULT NULL,
  `building_No` bigint(20) DEFAULT NULL
);

INSERT INTO Addresses VALUES (1111, 100, 1000, 76812,20);
INSERT INTO Addresses VALUES (2222, 101, 1002, 76815,22);
INSERT INTO Addresses VALUES (3333, 102, 1003, 76817,420);
INSERT INTO Addresses VALUES (4444, 101, 1004, 75555,2);
INSERT INTO Addresses VALUES (5555, 103, 1000, 76819,290);
INSERT INTO Addresses VALUES (6666, 104, 1002, 7888,8);

CREATE TABLE `Groups` (
  `group_id` int NOT NULL,
   PRIMARY KEY (group_id),
  `parent_group_id` int,
  `group_name` varchar(255) DEFAULT NULL,
  `group_type` varchar(255)
);

INSERT INTO Groups VALUES (1,0, 'Fox',"Clothes");
INSERT INTO Groups VALUES (2,0, 'Bezeq',"Network");
INSERT INTO Groups VALUES (3,1, 'Fox Kids',"Clothes for Kids");
INSERT INTO Groups VALUES (4,1, 'Fox Home',"Home accs.");
INSERT INTO Groups VALUES (5,1, 'Fox Fox',"Everything");
INSERT INTO Groups VALUES (6,2, 'Yes',"TV provide");

CREATE TABLE `Stores` (
  `stores_id` int NOT NULL,
   PRIMARY KEY (stores_id),
  `store_name` varchar(255) DEFAULT NULL,
  `address_id` int NOT NULL,
  `mall_id` int NOT null,
  `group_id` int NOT null,
  FOREIGN KEY (group_id) REFERENCES Groups(group_id)
);

INSERT INTO Stores VALUES (1000, 'Fox Acco',1111, 10000, 5);
INSERT INTO Stores VALUES (1001, 'Fox Eilat',2222, 10001, 4);
INSERT INTO Stores VALUES (1002, 'Fox BatYam',3333, 10001, 3);
INSERT INTO Stores VALUES (1003, 'Fox Metula',4444, 10003, 3);
INSERT INTO Stores VALUES (1004, 'Fox Managment',1111, 10002, 1);
INSERT INTO Stores VALUES (1005, 'Yes Shoham',5555, 10003, 6);
INSERT INTO Stores VALUES (1006, 'Yes Managment',1111, 10002, 2);
INSERT INTO Stores VALUES (1007, 'Yes Acco',1111, 10003, 6);


CREATE TABLE `Cities` (
  `city_id` int NOT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (city_id)
);
INSERT INTO Cities VALUES (100, 'Tel-Aviv');
INSERT INTO Cities VALUES (101, 'Acco');
INSERT INTO Cities VALUES (102, 'Eilat');
INSERT INTO Cities VALUES (103, 'Acco');
INSERT INTO Cities VALUES (104, 'Metula');
INSERT INTO Cities VALUES (105, 'Shoham');
INSERT INTO Cities VALUES (106, 'BatYam');


CREATE TABLE `Streets` (
  `street_id` int NOT NULL,
  `city_id` int NOT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (street_id)
);
INSERT INTO Streets VALUES (1000, 100,'Ben-Yehuda');
INSERT INTO Streets VALUES (1001, 101,'Bilu');
INSERT INTO Streets VALUES (1002, 100,'Arava');
INSERT INTO Streets VALUES (1003, 102,'Arava');
INSERT INTO Streets VALUES (1004, 103,'Negev');
INSERT INTO Streets VALUES (1005, 104, 'Sharon');

CREATE TABLE `Malls` (
  `mall_id` int NOT NULL,
  `mall_name` varchar(255) DEFAULT NULL,
  `mall_group_id` int NOT null,
  `address_id` int NOT null,
  PRIMARY KEY (mall_id),
  FOREIGN KEY (address_id) REFERENCES Addresses(address_id)
);

INSERT INTO Malls VALUES (10000, 'Azriely',2999,1111);
INSERT INTO Malls VALUES (10001, 'G',2998,2222);
INSERT INTO Malls VALUES (10002, 'Melisron',2997,3333);
INSERT INTO Malls VALUES (10003, 'Big',2996,4444);
INSERT INTO Malls VALUES (10004, 'ilanMalls',2995,1111);
INSERT INTO Malls VALUES (10005, 'YoyoMa',2994,5555);


CREATE TABLE `Employees` (
  `employee_id` int NOT NULL,
   PRIMARY KEY (employee_id),
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `stores_id` int,
  `group_id` int,
  FOREIGN KEY (stores_id) REFERENCES Stores(stores_id),
  FOREIGN KEY (group_id) REFERENCES Groups(group_id)
);

INSERT INTO Employees VALUES (2210, 'Guy', 'Bitan', 40,null,1);
INSERT INTO Employees VALUES (2211, 'Doron', 'Niv', 32,null,2);
INSERT INTO Employees VALUES (2212, 'Ilan', 'Valershtein', 40,1003,null);
INSERT INTO Employees VALUES (2213, 'Avi', 'Kuzi', 10,1005,null);
INSERT INTO Employees VALUES (2214, 'Shanni', 'Guy', 32,1006,null);
INSERT INTO Employees VALUES (2215, 'Amir', 'Kirsh', 40,1004,null);
