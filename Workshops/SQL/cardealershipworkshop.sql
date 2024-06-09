-- Create database
DROP DATABASE IF EXISTS cardealership;
CREATE DATABASE cardealership;
USE cardealership;

-- Create tables
CREATE TABLE dealerships (
    dealershipId INT AUTO_INCREMENT
    , name VARCHAR(50)
    , address VARCHAR(50)
    , phone VARCHAR(12)
    , PRIMARY KEY (dealershipId)
);

CREATE TABLE vehicles (
    vin VARCHAR(17)
    , year INT
    , make VARCHAR(50)
    , model VARCHAR(50)
    , vehicleType VARCHAR(50)
    , color VARCHAR(20)
    , odometer INT
    , price DOUBLE
    , sold BIT DEFAULT 0
    , PRIMARY KEY (vin)
);

CREATE TABLE inventory (
    dealershipId INT
    , vin VARCHAR(17)
    , FOREIGN KEY (dealershipId) REFERENCES dealerships(dealershipId)
    , FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE salesContracts (
    salesContractId INT AUTO_INCREMENT
    , vin VARCHAR(17)
    , contractDate DATE
    , customerName VARCHAR(100)
    , customerEmail VARCHAR(100)
    , salesTax DOUBLE
    , processingFee DOUBLE
    , recordingFee DOUBLE
    , totalPrice DOUBLE
    , monthlyPayment DOUBLE
    , financing BIT
    , addOns VARCHAR(255)
    , PRIMARY KEY (salesContractId)
    , FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

CREATE TABLE leaseContracts (
    leaseContractId INT AUTO_INCREMENT
    , vin VARCHAR(17)
    , contractDate DATE
    , customerName VARCHAR(100)
    , customerEmail VARCHAR(100)
    , endingValue DOUBLE
    , leaseFee DOUBLE
    , totalPrice DOUBLE
    , monthlyPayment DOUBLE
    , addOns VARCHAR(255)
    , PRIMARY KEY (leaseContractId)
    , FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

-- Populate tables with sample data
INSERT INTO dealerships (name, address, phone)
VALUES
    ('Antecennial Automobiles', '7228 rue de Boogie, Jamrock, Revachol West', '123-456-7890')
    , ('Insulinde Motorcars', '455 Grande Couron, Jamrock, Revachol West', '987-654-3210')
;

INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price)
VALUES
    ('1HGCM82633A123456', 1956, 'Coupris', '40', 'Sedan', 'Blue', 5000, 25000.00)
    , ('1FTFW1EF3EKF12345', 2021, 'FALN', 'A-6 Tempo', 'Truck', 'Beige', 10000, 45000.00)
    , ('1G1JC5444R7123456', 1950, 'Coupris', 'Kineema', 'Coupe', 'Blue', 8000, 35000.00)
;

INSERT INTO inventory (dealershipId, vin)
VALUES
    (1, '1HGCM82633A123456')
    , (1, '1FTFW1EF3EKF12345')
    , (2, '1G1JC5444R7123456')
;

INSERT INTO salesContracts (vin, contractDate, customerName, customerEmail, salesTax, processingFee, recordingFee, totalPrice, monthlyPayment, financing, addOns)
VALUES
    ('1HGCM82633A123456', '2023-06-10', 'Isaac Asimov', 'isaac.asimov@gmail.com', 1250.00, 495.00, 100.00, 26845.00, 618.27, 1, NULL)
;

INSERT INTO leaseContracts (vin, contractDate, customerName, customerEmail, endingValue, leaseFee, totalPrice, monthlyPayment, addOns)
VALUES
    ('1FTFW1EF3EKF12345', '2023-06-11', 'Ursula K. Le Guin', 'ursula.leguin@gmail.com', 22500.00, 3150.00, 33750.00, 850.00, NULL)
;

-- Test queries
-- 1 - Get all dealerships
SELECT * FROM dealerships;

-- 2 - Find all vehicles for a specific dealership
SELECT v.* 
FROM vehicles v
JOIN inventory i ON v.vin = i.vin
WHERE i.dealershipId = 1;

-- 3 - Find a car by VIN
SELECT * FROM vehicles WHERE vin = '1HGCM82633A123456';

-- 4 - Find dealership where a certain car is located, by VIN
SELECT d.* 
FROM dealerships d
JOIN inventory i ON d.dealershipId = i.dealershipId
WHERE i.vin = '1HGCM82633A123456';

-- 5 - Find all dealerships that have a certain car type (Red Ford Mustang)
SELECT d.*
FROM dealerships d
JOIN inventory i ON d.dealershipId = i.dealershipId
JOIN vehicles v ON i.vin = v.vin
WHERE v.make = 'Coupris' AND v.model = 'Kineema' AND v.color = 'Blue';

-- 6 - Get all sales information for a specific dealership for a specific date range
SELECT sc.* 
FROM salesContracts sc
JOIN vehicles v ON sc.vin = v.vin
JOIN inventory i ON v.vin = i.vin
WHERE i.dealershipId = 1 
AND sc.contractDate BETWEEN '2023-01-01' AND '2023-12-31'
;