CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    Name VARCHAR(100),
    Position VARCHAR(50),
    Salary DECIMAL(10,2),
    Department VARCHAR(50),
    HireDate DATE
);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES 
(1, 'Alice Johnson', 'Manager', 70000, 'HR', '2015-06-15'),
(2, 'Bob Brown', 'Developer', 60000, 'IT', '2017-03-20');

DROP PROCEDURE IF EXISTS EmployeeManagement_HireEmployee;

CREATE PROCEDURE EmployeeManagement_HireEmployee (
    IN empId INT,
    IN empName VARCHAR(100),
    IN position VARCHAR(50),
    IN salary DECIMAL(10,2),
    IN department VARCHAR(50),
    IN hireDate DATE
)
BEGIN
    INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
    VALUES (empId, empName, position, salary, department, hireDate);
END;

DROP PROCEDURE IF EXISTS EmployeeManagement_UpdateEmployeeDetails;

CREATE PROCEDURE EmployeeManagement_UpdateEmployeeDetails (
    IN empId INT,
    IN newName VARCHAR(100),
    IN newPosition VARCHAR(50),
    IN newDept VARCHAR(50)
)
BEGIN
    UPDATE Employees
    SET Name = newName,
        Position = newPosition,
        Department = newDept
    WHERE EmployeeID = empId;
END;

DROP FUNCTION IF EXISTS EmployeeManagement_CalculateAnnualSalary;

CREATE FUNCTION EmployeeManagement_CalculateAnnualSalary (
    empId INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE annualSalary DECIMAL(10,2);

    SELECT Salary * 12 INTO annualSalary
    FROM Employees
    WHERE EmployeeID = empId;

    RETURN annualSalary;
END;

CALL EmployeeManagement_HireEmployee(3, 'Irfaan', 'Analyst', 50000, 'Finance', CURDATE());

CALL EmployeeManagement_UpdateEmployeeDetails(3, 'Syed Irfaan', 'Sr. Analyst', 'Finance');

SELECT EmployeeManagement_CalculateAnnualSalary(3) AS AnnualSalary;
