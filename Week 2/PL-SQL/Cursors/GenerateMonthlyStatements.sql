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

DROP PROCEDURE IF EXISTS GenerateMonthlyStatements;

CREATE PROCEDURE GenerateMonthlyStatements()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE t_id INT;
    DECLARE acc_id INT;
    DECLARE c_id INT;
    DECLARE t_date DATE;
    DECLARE amt DECIMAL(10,2);
    DECLARE t_type VARCHAR(10);

    DECLARE cur CURSOR FOR
        SELECT t.TransactionID, a.CustomerID, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        WHERE MONTH(t.TransactionDate) = MONTH(CURDATE()) AND YEAR(t.TransactionDate) = YEAR(CURDATE());

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;

    get_transaction: LOOP
        FETCH cur INTO t_id, c_id, acc_id, t_date, amt, t_type;
        IF done THEN
            LEAVE get_transaction;
        END IF;

        SELECT CONCAT('Customer ', c_id, 
                      ' | Account ', acc_id, 
                      ' | ', t_type, 
                      ' ₹', amt, 
                      ' on ', t_date) AS Statement;
    END LOOP;

    CLOSE cur;
END;

CALL GenerateMonthlyStatements();
