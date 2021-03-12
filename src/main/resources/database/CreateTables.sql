DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer (
    CustomerId INTEGER PRIMARY KEY UNIQUE,
    Forename TEXT NOT NULL,
    Surname TEXT NOT NULL,
    Email TEXT NOT NULL,
    Password TEXT NOT NULL,
    AddressLine1 TEXT NOT NULL,
    AddressLine2 TEXT NOT NULL,
    City TEXT NOT NULL,
    Postcode TEXT,
    PhoneNumber TEXT NULL
);

DROP TABLE IF EXISTS Product;
CREATE TABLE Product (
    ProductId INTEGER PRIMARY KEY UNIQUE,
    Name TEXT NOT NULL,
    Description TEXT NOT NULL,
    Price REAL NOT NULL,
    StockLevel INTEGER NOT NULL,
    ImagePath TEXT NOT NULL
);

DROP TABLE IF EXISTS "Order";
CREATE TABLE "Order" (
    OrderId INTEGER PRIMARY KEY UNIQUE,
    CustomerId INTEGER NOT NULL,
    foreign key (CustomerId) REFERENCES Customer (CustomerId)
);

DROP TABLE IF EXISTS OrderLine;
CREATE TABLE OrderLine (
    Quantity INTEGER NOT NULL,
    OrderID INTEGER NOT NULL REFERENCES "Order" (OrderId),
    ProductID INTEGER NOT NULL REFERENCES "Product" (ProductId),
    primary key (OrderID, ProductID)
);