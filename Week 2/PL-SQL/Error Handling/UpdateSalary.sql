CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary DECIMAL(10,2),
    Department VARCHAR(50),
    HireDate DATE
);

CREATE TABLE ErrorLog (
    ErrorID INT AUTO_INCREMENT PRIMARY KEY,
    ErrorMessage VARCHAR(255),
    ErrorTime DATETIME DEFAULT NOW()
);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES 
(1, 'Alice Johnson', 'Manager', 70000, 'HR', '2015-06-15'),
(2, 'Bob Brown', 'Developer', 60000, 'IT', '2017-03-20');

DROP PROCEDURE IF EXISTS UpdateSalary;

CREATE PROCEDURE UpdateSalary(
    IN empId INT,
    IN percentage DECIMAL(5,2)
)
BEGIN
    DECLARE currentSalary DECIMAL(10,2);
    DECLARE err_msg VARCHAR(255);

    DECLARE CONTINUE HANDLER FOR NOT FOUND
    BEGIN
        SET err_msg = CONCAT('EmployeeID ', empId, ' does not exist.');
        INSERT INTO ErrorLog(ErrorMessage) VALUES (err_msg);
    END;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SET err_msg = CONCAT('SQL Error when updating EmployeeID ', empId);
        INSERT INTO ErrorLog(ErrorMessage) VALUES (err_msg);
    END;

    START TRANSACTION;

    SELECT Salary INTO currentSalary
    FROM Employees
    WHERE EmployeeID = empId
    FOR UPDATE;

    UPDATE Employees
    SET Salary = Salary + (Salary * (percentage / 100))
    WHERE EmployeeID = empId;

    COMMIT;
END;

CALL UpdateSalary(1, 10);

SELECT EmployeeID, Name, Salary FROM Employees WHERE EmployeeID = 1;

CALL UpdateSalary(99, 5);

SELECT * FROM ErrorLog;
