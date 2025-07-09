CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
    DOB DATE,
    Balance DECIMAL(10,2),
    LastModified DATETIME
);

CREATE TABLE Loans (
    LoanID INT PRIMARY KEY,
    CustomerID INT,
    LoanAmount DECIMAL(10,2),
    InterestRate DECIMAL(5,2),
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES 
(1, 'John Doe', '1985-05-15', 1000, NOW()),
(2, 'Jane Smith', '1990-07-20', 1500, NOW());


INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES 
(1, 1, 5000, 5, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 60 MONTH));

DROP FUNCTION IF EXISTS CalculateMonthlyInstallmentFromDates;

CREATE FUNCTION CalculateMonthlyInstallmentFromDates(
    loanAmount DECIMAL(10,2),
    annualRate DECIMAL(5,2),
    startDate DATE,
    endDate DATE
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE monthlyRate DECIMAL(10,6);
    DECLARE totalMonths INT;
    DECLARE emi DECIMAL(10,2);

    SET monthlyRate = annualRate / 1200; 
    SET totalMonths = PERIOD_DIFF(DATE_FORMAT(endDate, '%Y%m'), DATE_FORMAT(startDate, '%Y%m'));

    SET emi = (loanAmount * monthlyRate * POW(1 + monthlyRate, totalMonths)) /
              (POW(1 + monthlyRate, totalMonths) - 1);

    RETURN emi;
END;

SELECT 
    LoanID,
    LoanAmount,
    InterestRate,
    StartDate,
    EndDate,
    CalculateMonthlyInstallmentFromDates(LoanAmount, InterestRate, StartDate, EndDate) AS MonthlyEMI
FROM Loans;
