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

DROP PROCEDURE IF EXISTS UpdateLoanInterestRates;

CREATE PROCEDURE UpdateLoanInterestRates()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE loanId INT;
    DECLARE amount DECIMAL(10,2);
    DECLARE currentRate DECIMAL(5,2);
    DECLARE newRate DECIMAL(5,2);

    DECLARE loan_cursor CURSOR FOR
        SELECT LoanID, LoanAmount, InterestRate FROM Loans;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN loan_cursor;

    update_loop: LOOP
        FETCH loan_cursor INTO loanId, amount, currentRate;
        IF done THEN
            LEAVE update_loop;
        END IF;

        IF amount >= 100000 THEN
            SET newRate = currentRate + 0.5;
        ELSE
            SET newRate = currentRate + 1.0;
        END IF;

        UPDATE Loans
        SET InterestRate = newRate
        WHERE LoanID = loanId;
    END LOOP;

    CLOSE loan_cursor;
END;

CALL UpdateLoanInterestRates();

SELECT LoanID, LoanAmount, InterestRate FROM Loans;

