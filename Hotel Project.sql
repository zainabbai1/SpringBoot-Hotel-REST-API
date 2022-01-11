create database Hotelapp;
use Hotelapp;

create table employees(id bigint primary key auto_increment,name varchar(255),surname varchar(255),email varchar(255),password varchar(255));
select * from employees;

create table guests(id bigint primary key auto_increment,name varchar(255),surname varchar(255),email varchar(255),phoneNumber varchar(255),address varchar(255),city varchar(255),country varchar(255),personalId varchar(255));
select * from guests;

create table reservations(id bigint primary key auto_increment,startDate date,endDate date,roomId int,guestId int,price int);
select * from reservations;

create table rooms(id bigint primary key auto_increment,roomNumber int,floor int,people int,price int,is_available boolean);
select * from rooms;
