create table if not exists Ingredient (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null
);

create table if not exists Taco (
id identity,
name varchar(50) not null,
createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
taco bigint not null,
ingredient varchar(4) not null
);

alter table Taco_Ingredients
add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order (
id identity,
deliveryname varchar(50) not null,
deliverystreet varchar(50) not null,
deliverycity varchar(50) not null,
deliverystate varchar(10) not null,
deliveryzip varchar(10) not null,
ccnumber varchar(16) not null,
ccexpiration varchar(5) not null,
ccCVV varchar(3) not null,
placedat timestamp not null,
person bigint not null
);



create table if not exists Taco_Order_Tacos (
orderjpa_id bigint not null,
tacos_id bigint not null
);

create table if not exists users (
username varchar(20) not null,
password varchar(20) not null,
enabled boolean 
);

create table  if not exists authorities(
username varchar(20) not null,
authority varchar(20) not null
);

create table if not exists Person (

id identity,
username varchar(20) not null,
password varchar(80) not null,
fullname varchar(20),
street varchar(60),
city varchar(20),
state varchar(20),
zip varchar(10),
phone varchar(20)
);

alter table Taco_Order_Tacos
add foreign key (orderjpa_id) references Taco_Order(id);
alter table Taco_Order_Tacos
add foreign key (tacos_id) references Taco(id);

alter table Taco_Order
add foreign key (person) references Person(id);

