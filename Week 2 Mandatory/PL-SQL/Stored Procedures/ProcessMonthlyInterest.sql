CREATE TABLE Accounts (
  AccountID INT PRIMARY KEY,
  CustomerID INT,
  AccountType VARCHAR(20),
  Balance INT,
  LastModified DATE
);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES 
(1, 1, 'Savings', 1000, CURRENT_DATE),
(2, 2, 'Checking', 1500, CURRENT_DATE);

SELECT * FROM Accounts;

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
 UPDATE Accounts
 SET Balance = Balance + (Balance * 0.01),
     LastModified = CURRENT_DATE
 WHERE AccountType = 'Savings';
END;

CALL ProcessMonthlyInterest();

SELECT * FROM Accounts;
