
-- creating a database
create database employeedb;

-- selecting the database
use employeedb;

-- creating a table named employee
create table employee (
    empid int primary key,
    Name varchar(50),
    domain varchar(50)
);

-- selecting the employee table and showing all the column and it's values
select * from employee;

-- inserting values to the employee table
insert into employee values (1,"Boopathi","Java"),(2,"Kesav","Java");

--removing all the elements and its structure in employee 
-- truncate table employee;

--renaming the empoloyee table to employeedetails
rename table employee to employeedetails;

--selecting the employeedetails
select * from employeedetails;

-- Add a new column 'age' of type INT to the EmployeeDetails table
alter table employeedetails add age int;

-- Start a new transaction block
start transaction;

-- Update the 'age' column to 10 where the employee ID (EmpId) is 1
update employeedetails set age=10 where empid=1;

-- Update the 'age' column to 12 where the employee ID (EmpId) is 2
update employeedetails set age=12 where empid=2;

-- Save the current state of the transaction at this point, creating a savepoint named 'B'
savepoint B;

-- Delete the record of the employee with employee ID 2
delete from employeedetails where empid=2;

-- Roll back the changes made after the savepoint 'B', undoing the deletion of employee with EmpId 2
rollback to savepoint B;

-- Drop (delete) the EmployeeDetails table from the database
drop table employeedetails;

-- Commit the transaction, saving all changes made before the savepoint
commit;

-- Drop (delete) the Employee table from the database
drop table employee;

-- Drop (delete) the employeedb database entirely
drop database employeedb;
