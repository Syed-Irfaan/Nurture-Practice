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

DROP PROCEDURE IF EXISTS ApplyAnnualFee;

CREATE PROCEDURE ApplyAnnualFee()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE accId INT;
    DECLARE fee DECIMAL(10,2) DEFAULT 100.00;

    DECLARE acc_cursor CURSOR FOR
        SELECT AccountID FROM Accounts;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN acc_cursor;

    fee_loop: LOOP
        FETCH acc_cursor INTO accId;
        IF done THEN
            LEAVE fee_loop;
        END IF;

        UPDATE Accounts
        SET Balance = Balance - fee,
            LastModified = NOW()
        WHERE AccountID = accId;
    END LOOP;

    CLOSE acc_cursor;
END;

SELECT AccountID, Balance FROM Accounts;

CALL ApplyAnnualFee();

SELECT AccountID, Balance FROM Accounts;
