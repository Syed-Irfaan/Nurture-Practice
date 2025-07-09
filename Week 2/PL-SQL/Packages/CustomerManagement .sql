CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(10,2),
    LastModified DATETIME
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES 
(1, 'John Doe', '1985-05-15', 1000, NOW()),
(2, 'Jane Smith', '1990-07-20', 1500, NOW());

DROP PROCEDURE IF EXISTS CustomerManagement_AddCustomer;

CREATE PROCEDURE CustomerManagement_AddCustomer (
    IN custId INT,
    IN custName VARCHAR(100),
    IN dob DATE,
    IN balance DECIMAL(10,2)
)
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (custId, custName, dob, balance, NOW());
END;

DROP PROCEDURE IF EXISTS CustomerManagement_UpdateDetails;

CREATE PROCEDURE CustomerManagement_UpdateDetails (
    IN custId INT,
    IN newName VARCHAR(100),
    IN newDOB DATE
)
BEGIN
    UPDATE Customers
    SET Name = newName,
        DOB = newDOB,
        LastModified = NOW()
    WHERE CustomerID = custId;
END;

DROP FUNCTION IF EXISTS CustomerManagement_GetBalance;

CREATE FUNCTION CustomerManagement_GetBalance (
    custId INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE custBal DECIMAL(10,2);

    SELECT Balance INTO custBal
    FROM Customers
    WHERE CustomerID = custId;

    RETURN custBal;
END;
CALL CustomerManagement_AddCustomer(3, 'Irfaan', '2003-02-15', 2000);

CALL CustomerManagement_UpdateDetails(3, 'Syed Irfaan', '2003-02-14');

SELECT CustomerManagement_GetBalance(3) AS Balance;

