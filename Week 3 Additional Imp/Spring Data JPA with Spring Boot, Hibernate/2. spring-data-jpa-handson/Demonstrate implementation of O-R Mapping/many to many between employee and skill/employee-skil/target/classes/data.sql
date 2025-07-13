
INSERT INTO department (dp_id, dp_name) VALUES (1, 'Engineering'), (2, 'HR');
INSERT INTO skill (id, name) VALUES (1, 'Java'), (2, 'Spring Boot'), (3, 'SQL');
INSERT INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) VALUES
(1, 'Alice', 60000.00, true, '1990-01-01', 1),
(2, 'Bob', 50000.00, true, '1985-05-15', 1),
(3, 'Charlie', 40000.00, false, '1992-07-22', 2);
INSERT INTO employee_skill (es_em_id, es_sk_id) VALUES (1, 1), (1, 2), (2, 3);
