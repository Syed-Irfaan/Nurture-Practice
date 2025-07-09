CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(10,2),
    LastModified DATETIME
);

CREATE TABLE Accounts (
    AccountID INT PRIMARY KEY,
    CustomerID INT,
    AccountType VARCHAR(20),
    Balance DECIMAL(10,2),
    LastModified DATETIME,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES 
(1, 'John Doe', '1985-05-15', 1000, NOW()),
(2, 'Jane Smith', '1990-07-20', 1500, NOW());

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES 
(1, 1, 'Savings', 1000, NOW()),
(2, 2, 'Checking', 1500, NOW());

DROP PROCEDURE IF EXISTS AccountOperations_OpenAccount;

CREATE PROCEDURE AccountOperations_OpenAccount (
    IN accId INT,
    IN custId INT,
    IN accType VARCHAR(20),
    IN initialBalance DECIMAL(10,2)
)
BEGIN
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (accId, custId, accType, initialBalance, NOW());
END;

DROP PROCEDURE IF EXISTS AccountOperations_CloseAccount;

CREATE PROCEDURE AccountOperations_CloseAccount (
    IN accId INT
)
BEGIN
    DELETE FROM Accounts WHERE AccountID = accId;
END;

DROP FUNCTION IF EXISTS AccountOperations_GetTotalBalance;

CREATE FUNCTION AccountOperations_GetTotalBalance (
    custId INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total DECIMAL(10,2);

    SELECT IFNULL(SUM(Balance), 0) INTO total
    FROM Accounts
    WHERE CustomerID = custId;

    RETURN total;
END;

CALL AccountOperations_OpenAccount(10, 1, 'Savings', 2000);

SELECT AccountOperations_GetTotalBalance(1) AS TotalBalance;

CALL AccountOperations_CloseAccount(10);
