-- Customers Table
CREATE TABLE Customers (
  CustomerID INT PRIMARY KEY,
  Name VARCHAR(100),
  DOB DATE,
  Balance DECIMAL(10,2),
  LastModified DATE
);

-- Accounts Table
CREATE TABLE Accounts (
  AccountID INT PRIMARY KEY,
  CustomerID INT,
  AccountType VARCHAR(20),
  Balance DECIMAL(10,2),
  LastModified DATE,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Transactions Table
CREATE TABLE Transactions (
  TransactionID INT PRIMARY KEY,
  AccountID INT,
  TransactionDate DATE,
  Amount DECIMAL(10,2),
  TransactionType VARCHAR(10),
  FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

-- Loans Table
CREATE TABLE Loans (
  LoanID INT PRIMARY KEY,
  CustomerID INT,
  LoanAmount DECIMAL(10,2),
  InterestRate DECIMAL(5,2),
  StartDate DATE,
  EndDate DATE,
  FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Employees Table
CREATE TABLE Employees (
  EmployeeID INT PRIMARY KEY,
  Name VARCHAR(100),
  Position VARCHAR(50),
  Salary DECIMAL(10,2),
  Department VARCHAR(50),
  HireDate DATE
);


-- Customers
INSERT INTO Customers VALUES
(1, 'John Doe', '1985-05-15', 1000, CURDATE()),
(2, 'Jane Smith', '1990-07-20', 1500, CURDATE());

-- Accounts
INSERT INTO Accounts VALUES
(1, 1, 'Savings', 1000, CURDATE()),
(2, 2, 'Checking', 1500, CURDATE());

-- Transactions
INSERT INTO Transactions VALUES
(1, 1, CURDATE(), 200, 'Deposit'),
(2, 2, CURDATE(), 300, 'Withdrawal');

-- Loans
INSERT INTO Loans VALUES
(1, 1, 5000, 5.00, CURDATE(), CURDATE() + INTERVAL 60 MONTH);

-- Employees
INSERT INTO Employees VALUES
(1, 'Alice Johnson', 'Manager', 70000, 'HR', '2015-06-15'),
(2, 'Bob Brown', 'Developer', 60000, 'IT', '2017-03-20');

ALTER TABLE Customers ADD COLUMN IsVIP BOOLEAN DEFAULT FALSE;

UPDATE Customers
SET IsVIP = TRUE
WHERE Balance > 10000;

SELECT 
  CustomerID,
  Name,
  Balance,
  IF(IsVIP, 'TRUE', 'FALSE') AS IsVIP_Status
FROM Customers;
