#######Script########


#####Creating Customer#######

DROP TABLE IF EXISTS Customer
CREATE TABLE Customer (
CustomerId INTEGER PRIMARY KEY UNIQUE,
Forename TEXT   NOT NULL,
Surname TEXT    NOT NULL,
Email TEXT  NOT NULL,
Password TEXT   NOT NULL,
AddressLine1 TEXT NOT NULL,
AddressLine2 TEXT NOT NULL,
City    TEXT NOT NULL,
Postcode    TEXT,
PhoneNumber TEXT NULL
);


#####Creating Product########

DROP TABLE IF EXISTS Product
CREATE TABLE Product (
ProductId INTEGER PRIMARY KEY UNIQUE,
Name TEXT   NOT NULL,
Description TEXT    NOT NULL,
Price REAL  NOT NULL,
ImagePath TEXT  NOT NULL
);



#####Creating Order#######


DROP TABLE IF EXISTS Order
CREATE TABLE Order (
OrderId INTEGER PRIMARY KEY UNIQUE,
CustomerId INTEGER  NOT NULL
FOREIGN_KEY (CustomerId)
    REFERENCES Customer (CustomerId)
);



######Order Line######

DROP TABLE IF EXISTS OrderLine
CREATE TABLE OrderLine (
OrderId INTEGER PRIMARY KEY UNIQUE,
CustomerId INTEGER  NOT NULL
FOREIGN_KEY (OrderId)
    REFERENCES Order (OrderId)
FOREIGN_KEY (ProductId)
    REFERENCES Product (ProductId)
);


#########END########
