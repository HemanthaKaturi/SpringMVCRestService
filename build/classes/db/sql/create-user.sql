create table USER (UUID varchar(10) primary key,
FIRST_NAME varchar(32) not null,
MIDDLE_NAME varchar(32),
LAST_NAME varchar(32) not null,  
AGE Integer not null, 
GENDER varchar(1) not null, 
PHONE_NUMBER varchar(10) not null,
ZIPCODE Integer);