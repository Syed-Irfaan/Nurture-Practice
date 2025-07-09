
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

CREATE TABLE ErrorLog (
    ErrorID INT AUTO_INCREMENT PRIMARY KEY,
    ErrorMessage VARCHAR(255),
    ErrorTime DATETIME DEFAULT NOW()
);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES 
(1, 1, 'Savings', 1000, NOW()),
(2, 2, 'Checking', 1500, NOW());

INSERT INTO Transactions (AccountID, TransactionDate, Amount, TransactionType)
VALUES 
(1, NOW(), 200, 'Deposit'),
(2, NOW(), 300, 'Withdrawal');

CREATE PROCEDURE SafeTransferFunds(
    IN fromAccountId INT,
    IN toAccountId INT,
    IN amount DECIMAL(10,2)
)
BEGIN
    DECLARE fromBalance DECIMAL(10,2);
    DECLARE err_msg VARCHAR(255);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET err_msg = 'Error during fund transfer';
        INSERT INTO ErrorLog(ErrorMessage) VALUES (err_msg);
    END;

    START TRANSACTION;

    SELECT Balance INTO fromBalance
    FROM Accounts
    WHERE AccountID = fromAccountId
    FOR UPDATE;

    IF fromBalance < amount THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient funds for transfer';
    END IF;

    UPDATE Accounts
    SET Balance = Balance - amount, LastModified = NOW()
    WHERE AccountID = fromAccountId;

    UPDATE Accounts
    SET Balance = Balance + amount, LastModified = NOW()
    WHERE AccountID = toAccountId;

    INSERT INTO Transactions(AccountID, TransactionDate, Amount, TransactionType)
    VALUES 
    (fromAccountId, NOW(), amount, 'Transfer Out'),
    (toAccountId, NOW(), amount, 'Transfer In');

    COMMIT;
END;

CALL SafeTransferFunds(1, 2, 500);

SELECT * FROM Accounts;
SELECT * FROM Transactions;
SELECT * FROM ErrorLog;
