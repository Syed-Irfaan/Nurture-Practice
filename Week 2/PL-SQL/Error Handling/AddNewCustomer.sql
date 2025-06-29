CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(10,2),
    LastModified DATETIME
);

CREATE TABLE ErrorLog (
    ErrorID INT AUTO_INCREMENT PRIMARY KEY,
    ErrorMessage VARCHAR(255),
    ErrorTime DATETIME DEFAULT NOW()
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES 
(1, 'John Doe', '1985-05-15', 1000, NOW()),
(2, 'Jane Smith', '1990-07-20', 1500, NOW());

DROP PROCEDURE IF EXISTS AddNewCustomer;

CREATE PROCEDURE AddNewCustomer(
    IN custId INT,
    IN custName VARCHAR(100),
    IN dob DATE,
    IN balance DECIMAL(10,2)
)
BEGIN
    DECLARE custExists INT DEFAULT 0;
    DECLARE err_msg VARCHAR(255);

    SELECT COUNT(*) INTO custExists
    FROM Customers
    WHERE CustomerID = custId;

    IF custExists > 0 THEN
        SET err_msg = CONCAT('CustomerID ', custId, ' already exists.');
        INSERT INTO ErrorLog(ErrorMessage) VALUES (err_msg);
    ELSE
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (custId, custName, dob, balance, NOW());
    END IF;
END;

CALL AddNewCustomer(3, 'Michael Green', '1992-08-25', 2000);

CALL AddNewCustomer(1, 'Duplicate John', '1985-05-15', 3000);

SELECT * FROM Customers;

SELECT * FROM ErrorLog;
