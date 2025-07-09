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

CREATE PROCEDURE TransferFunds(IN fromAcc INT, IN toAcc INT, IN amount INT)
BEGIN
  DECLARE fromBalance INT;

  SELECT Balance INTO fromBalance FROM Accounts WHERE AccountID = fromAcc;

  IF fromBalance >= amount THEN
    UPDATE Accounts
    SET Balance = Balance - amount, LastModified = CURRENT_DATE
    WHERE AccountID = fromAcc;

    UPDATE Accounts
    SET Balance = Balance + amount, LastModified = CURRENT_DATE
    WHERE AccountID = toAcc;
  ELSE
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance in source account';
  END IF;
END;

CALL TransferFunds(1, 2, 1000);

SELECT * FROM Accounts;
