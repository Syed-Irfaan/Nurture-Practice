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

CREATE TABLE Transactions (
    TransactionID INT AUTO_INCREMENT PRIMARY KEY,
    AccountID INT,
    TransactionDate DATETIME,
    Amount DECIMAL(10,2),
    TransactionType VARCHAR(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES 
(1, 'John Doe', '1985-05-15', 1000, NOW()),
(2, 'Jane Smith', '1990-07-20', 1500, NOW());

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES 
(1, 1, 'Savings', 1000, NOW()),
(2, 2, 'Checking', 1500, NOW());

INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
VALUES 
(1, NOW(), 200, 'Deposit'),
(2, NOW(), 300, 'Withdrawal');

DROP TRIGGER IF EXISTS CheckTransactionRules;

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE currentBalance DECIMAL(10,2);

    SELECT Balance INTO currentBalance
    FROM Accounts
    WHERE AccountID = NEW.AccountID;

    IF NEW.TransactionType = 'Deposit' AND NEW.Amount <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Deposit amount must be positive';
    END IF;

    IF NEW.TransactionType = 'Withdrawal' AND NEW.Amount > currentBalance THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient balance for withdrawal';
    END IF;
END;

INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, NOW(), 200.00, 'Deposit'); 

INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, NOW(), -100.00, 'Deposit'); 

INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, NOW(), 2000.00, 'Withdrawal');

