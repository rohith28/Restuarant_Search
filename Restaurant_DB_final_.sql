/*******************************************************************************
   Restaurant Database - Version 1.4
   Script: Restaurant_DB.sql
   Description: Creates and populates the Restaurant database.
   DB Server: MySqlql
   Author: Mounika Uppala,Rohith Uppala
********************************************************************************/

/*********************************************************
Creating the Database in MY SQL
****************************************************************/
create database rest_db;
USE rest_db;



/*******************************************************************************
   Drop Foreign Keys Constraints
********************************************************************************/




/*******************************************************************************
   Drop Tables
********************************************************************************/
DROP TABLE IF EXISTS [Restaurant];

DROP TABLE IF EXISTS [FoodItem];

DROP TABLE IF EXISTS [RestaurantMenu];

DROP TABLE IF EXISTS [feedback];

DROP TABLE IF EXISTS [Customer];

/*******************************************************************************
   Create Tables
********************************************************************************/

create table Restaurant(
rid int not null AUTO_INCREMENT,
name varchar(200) not null,
address varchar(1000) not null,
phone bigint not null,
timmings varchar(200) not null,
primary key(rid)
);


create table FoodItem(
fid int not null AUTO_INCREMENT,
name varchar(200) not null,
cost varchar(200) not null,
primary key(fid)
);

create table RestaurantMenu(
menu int not null,
foreign key (menu) references Restaurant(rid) on update cascade on delete cascade,
foodItem int not null,
foreign key(foodItem) references FoodItem(fid) on update cascade on delete cascade,
primary key(menu, foodItem)
);

create table Customer(
cid int not null AUTO_INCREMENT,
name varchar(200) not null,
email varchar(200) not null,
primary key(cid,email)
);

create table feedback(
resid int not null,
foreign key (resid) references Restaurant(rid) on update cascade on delete cascade,
custid int not null,
foreign key (custid) references Customer(cid) on update cascade on delete cascade,
rating int not null,
date date,
primary key(resid,custid)
);

/*******************************************************************************
   Create Primary Key Unique Indexes
********************************************************************************/

/*******************************************************************************
   Create Foreign Keys Indexes
********************************************************************************/

CREATE INDEX [IFK_MenuId] ON [RestaurantMenu] ([menu]);
CREATE INDEX [IFK_FoodItemId ON [RestaurantMenu ] ([foodItem]);
CREATE INDEX [IFK_RestaurantId] ON [feedback] ([resid]);
CREATE INDEX [IFK_CustomerId] ON [feedback] ([custid]);

/*******************************************************************************
   Populate Tables
********************************************************************************/
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Sebastians','Quincy',6172070781,'7:00 am - 5:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Shabu Restaurant','Quincy',6172070782,'9:00 am - 5:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('99 Restaurant','Quincy',7632070782,'10:00 am - 10:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('La Paloma','Quincy',6177730512,'10:30 am - 10:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Grumpy Whites Restaurant','Quincy',6177702835,'11:30 am - 10:00 pm' );

INSERT INTO Restaurant(name,address,phone,timmings) VALUES('The Capital Grille','Boston',6172628900,'10:30 am - 01:00 am' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Papa-Razzi','Boston',6175369200,'11:30 am - 10:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Atlantic Fish Company','Boston',6172674000,'11:30 am - 11:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Venezia Restaurant','Boston',6174363120,'11:30 am - 09:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Abe & Louies','Boston',6175366300,'11:30 am - 11:00 pm' );

INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Giulia','Cambridge',6174412800,'05:30 am - 10:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Area Four','Cambridge',6177584444,'10:30 am - 09:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Oleana','Cambridge',6176610505,'11:00 am - 11:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Tupelo Restaurant','Cambridge',6174363122,'11:30 am - 09:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Evoo Restaurant','Cambridge',6175366303,'09:30 am - 10:00 pm' );

INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Mee King Garden','bridgewater',6174412803,'07:30 am - 11:00 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Chatta Box','bridgewater',6177584333,'11:30 am - 10:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('SugarCane','bridgewater',6176610515,'11:30 am - 11:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Crispis Italian Cuisine','bridgewater',6174363129,'11:30 am - 09:30 pm' );
INSERT INTO Restaurant(name,address,phone,timmings) VALUES('Riviera Cafe Brewhouse','bridgewater',6175366303,'09:30 am - 10:00 pm' );


INSERT INTO FoodItem(name,cost) VALUES('Biryani','$12.99');
INSERT INTO FoodItem(name,cost) VALUES('Biryani','$12.99');
INSERT INTO FoodItem(name,cost) VALUES('Pizza','$13.99');
INSERT INTO FoodItem(name,cost) VALUES('Burrito','$9.99');
INSERT INTO FoodItem(name,cost) VALUES('Quesadilla','$12.99');
INSERT INTO FoodItem(name,cost) VALUES('Fired Rice','$12.99');

INSERT INTO FoodItem(name,cost) VALUES('Spring Rolls','$5.99');
INSERT INTO FoodItem(name,cost) VALUES('Boba tea','$3.99');
INSERT INTO FoodItem(name,cost) VALUES('Masala Chai','$4.99');
INSERT INTO FoodItem(name,cost) VALUES('French fries','$3.99');
INSERT INTO FoodItem(name,cost) VALUES('Masala Dosa','$9.99');


INSERT INTO RestaurantMenu(menu,foodItem) VALUES (1,1); 
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (1,8);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (2,2);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (3,3);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (4,4);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (5,5);   

INSERT INTO RestaurantMenu(menu,foodItem) VALUES (6,6);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (7,7);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (8,8);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (9,9);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (10,10);   

INSERT INTO RestaurantMenu(menu,foodItem) VALUES (11,11);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (12,1);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (13,2);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (14,3);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (15,4);   

INSERT INTO RestaurantMenu(menu,foodItem) VALUES (16,5);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (17,6);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (18,7);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (19,8);   
INSERT INTO RestaurantMenu(menu,foodItem) VALUES (20,9);   

INSERT INTO Customer(name,email) VALUE('Admin','Admin@gmail.com');

INSERT INTO feedback(resid,custid,rating,date) VALUES (1,1,3,DATE '2017-05-02');
INSERT INTO feedback(resid,custid,rating,date) VALUES (2,1,3,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (3,1,4,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (4,1,5,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (5,1,3,DATE '2017-05-02'); 

INSERT INTO feedback(resid,custid,rating,date) VALUES (6,1,4,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (7,1,5,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (8,1,2,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (9,1,3,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (10,1,5,DATE '2017-05-02'); 

INSERT INTO feedback(resid,custid,rating,date) VALUES (11,1,3,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (12,1,2,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (13,1,5,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (14,1,4,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (15,1,3,DATE '2017-05-02'); 

INSERT INTO feedback(resid,custid,rating,date) VALUES (16,1,4,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (17,1,5,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (18,1,2,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (19,1,3,DATE '2017-05-02'); 
INSERT INTO feedback(resid,custid,rating,date) VALUES (20,1,4,DATE '2017-05-02'); 




 






















