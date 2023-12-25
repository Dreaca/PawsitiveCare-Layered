drop database if exists pawsitiveCare;

create database pawsitiveCare;

use pawsitiveCare;

create table user(
	userId varchar(10) primary key not null,
	userName varchar(35) not null,
	password varchar(40) not null
	);

create table veterinarian(
	vetId varchar(10) primary key not null,
	name varchar(35) not null,
	userId varchar(10) not null,
	contact varchar(20) not null,
	email varchar(30),
	foreign key (userId) references user(userId) on update cascade on delete cascade
	);

create table schedule(
	scheduleId varchar(10) primary key not null,
	date date,
	duration varchar(10) not null,
	time time(2)
	);

create table vet_schedule(
	scheduleId varchar(10) not null,
	vetId varchar(10) not null,
	foreign key (scheduleId) references schedule(scheduleId) on update cascade on delete cascade,
	foreign key (vetId) references veterinarian(vetId) on update cascade on delete cascade
	);

create table customer(
	custId varchar(10) primary key not null,
	name varchar(45) not null,
	address varchar(50) not null
	);
create table contact(
	custId varchar(10) not null,
	contId varchar(10) primary key not null,
	contact varchar(25) not null,
	foreign key (custId) references customer(custId) on update cascade on delete cascade
	);
create table appointment(
	appId varchar(10) primary key not null,
	custId varchar(10) not null,
	type ENUM('Checkup','Surgery','Vaccination') not null,
	time time(2),
	date varchar(20),
	price double,
	constraint foreign key (custId) references customer(custId) on update cascade on delete cascade
	);
create table pet(
	petId varchar(10) primary key not null,
	name varchar(20) not null,
	age int not null,
	breed varchar(10) not null,
	gender ENUM('Male','Female') not null,
	color varchar (20) not null,
	custId varchar(10) not null,
	constraint foreign key (custId) references customer(custId) on update cascade on delete cascade
	);
create table record(
	petId varchar(10),
	recordId varchar(10) primary key not null,
	description varchar(50),
	date date,
	constraint foreign key(petId) references pet(petId) on update cascade on delete cascade
	);
create table orders(
	orderId varchar(10) primary key not null,
	custId varchar(10),
	date date,
	constraint foreign key(custId) references customer(custId) on update cascade on delete cascade
	);
create table item(
	itemId varchar(10) primary key not null,
	description varchar(20) not null,
	qtyOnHand int,
	unitPrice float
	);
create table orderDetail(
	orderId varchar(10),
	itemId varchar(10),
	qty int,
	unitPrice float,
	constraint foreign key(orderId) references orders(orderId) on update cascade on delete cascade,
	constraint foreign key (itemId) references item(itemId) on update cascade on delete cascade
	);
create table supplier(
	suppId varchar(10) primary key not null,
	location varchar(10),
	name varchar(30) not null,
	type varchar(30) not null,
	contact varchar(20)
	);
create table item_supplier(
    suppOrderId varchar(10) primary key,
	itemId varchar(10),
	suppId varchar(10),
	qty int,
	date date,
	constraint foreign key(itemId) references item(itemId) on update cascade on delete cascade,
	constraint foreign key (suppId) references supplier(suppId) on update cascade on delete cascade
);

create table employee(
    employeeId varchar(10) primary key not null,
    name varchar (25),
    address varchar(50),
    contact varchar(25),
    salary double,
    userId varchar(10),
    NIC varchar (50),
    image mediumblob,
    constraint foreign key (userId) references user(userId) on update cascade on delete cascade
);