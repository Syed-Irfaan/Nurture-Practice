CREATE TABLE Employees (
  EmployeeID INT PRIMARY KEY,
  Name VARCHAR(100),
  Position VARCHAR(50),
  Salary INT,
  Department VARCHAR(50),
  HireDate DATE
);

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES 
(1, 'Alice Johnson', 'Manager', 70000, 'HR', '2015-06-15'),
(2, 'Bob Brown', 'Developer', 60000, 'IT', '2017-03-20');

SELECT * FROM Employees;

CREATE PROCEDURE UpdateEmployeeBonus(IN deptName VARCHAR(50), IN bonusPercent DECIMAL(5,2))
BEGIN
  UPDATE Employees
  SET Salary = Salary + (Salary * bonusPercent / 100)
  WHERE Department = deptName;
END;

CALL UpdateEmployeeBonus('HR', 10);

SELECT * FROM Employees;
