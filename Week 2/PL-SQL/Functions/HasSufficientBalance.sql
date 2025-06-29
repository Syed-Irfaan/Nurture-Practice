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

DROP FUNCTION IF EXISTS HasSufficientBalance;

CREATE FUNCTION HasSufficientBalance(
    accId INT,
    amt DECIMAL(10,2)
)
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE accBalance DECIMAL(10,2) DEFAULT 0;

    SELECT Balance INTO accBalance
    FROM Accounts
    WHERE AccountID = accId;

    RETURN accBalance >= amt;
END;

SELECT AccountID, Balance, HasSufficientBalance(AccountID, 800) AS IsEnough
FROM Accounts;


