--REZA_ASADIPOUR_HW09_Maktab146

/*Question 1: Display the concatenation of first_name and last_name with a space in
between as FULL_NAME, and the number of characters in the last
name as LASTNAME_LENGTH for female employees whose hire_date is between '1990-
01-01' and '1995-12-31' and whose first_name starts with 'C' or ends with 'a'.
Hint: PostgreSQL has a function that returns the length of a string. You can
use LENGTH(column_name) to get the number of characters in a text column. For
example, LENGTH('hello') returns 5. Apply this function to the last_name column.*/
SELECT CONCAT(first_name, ' ', last_name) AS FULL_NAME, LENGTH(last_name) AS LASTNAME_LENGTH
FROM employees
WHERE gender = 'F'
  AND hire_date BETWEEN '1990-01-01' AND '1995-12-31'
  AND (first_name LIKE 'C%' OR first_name LIKE '%a');

SELECT first_name || ' ' || last_name AS FULL_NAME,
       LENGTH(last_name)              AS LASTNAME_LENGTH
FROM employees
WHERE gender = 'F'
  AND hire_date BETWEEN '1990-01-01' AND '1995-12-31'
  AND (first_name LIKE 'C%' OR first_name LIKE '%a');

/*Question 2: Count the number of employees whose birth_date is after '1965-01-01' and
whose gender is 'M' or whose first_name contains 'an' and hire_date is before '1988-01-01'.
Name the result column EMPLOYEE_COUNT. (Pay attention to operator precedence)*/
SELECT COUNT(*) AS EMPLOYEE_COUNT
FROM employees
WHERE birth_date > '1965-01-01'
  AND (gender = 'M' OR first_name LIKE '%an%')
  AND hire_date < '1988-01-01';

/*Question 3: Display the maximum salary as MAX_SALARY and the minimum salary as
MIN_SALARY from the salaries table, but only for salaries that are between 50000 and
120000 and where the emp_no is in (10001, 10005, 10010, 10015, 10020).*/
SELECT MAX(salary) AS MAX_SALARY,
       MIN(salary) AS MIN_SALARY
FROM salaries
WHERE salary BETWEEN 50000 AND 120000
  AND emp_no IN (10001, 10005, 10010, 10015, 10020);

/*Question 4: From the employees table, display emp_no and a concatenated column in the
format [first_name] [last_name] (born: [birth_date]) as EMPLOYEE_INFO for employees
whose last_name is not NULL and whose first_name like pattern '%e%' but not like '%a%',
and whose hire_date is after '1987-01-01'.*/
SELECT emp_no,
       CONCAT(first_name, ' ', last_name, ' (born: ', birth_date, ')') AS EMPLOYEE_INFO
FROM employees
WHERE last_name IS NOT NULL
  AND first_name LIKE '%e%'
  AND first_name NOT LIKE '%a%'
  AND hire_date > '1987-01-01';

/*Question 5: Find the average salary as AVG_SALARY and the total sum of salaries as
TOTAL_SALARY from the salaries table, but only for salaries greater than 60000 and less
than 150000, and where to_date is NULL or to_date is after '2000-01-01'.*/
SELECT AVG(salary) AS AVG_SALARY, SUM(salary) AS TOTAL_SALARY
FROM salaries
WHERE salary > 60_000
  AND salary < 150000
  AND (to_date IS NULL OR to_date > '2000-01-01');

/*Question 6: Display first_name, last_name, and hire_date from employees where hire_date
is between '1985-01-01' and '1992-12-31' and (gender = 'F' or last_name starts with 'S') and
first_name does not contain 'son'. Use COALESCE to replace any NULL last_name with 'NO_LASTNAME'.*/
SELECT first_name, COALESCE(last_name, 'NO_LASTNAME') AS last_name, hire_date
FROM employees
WHERE hire_date BETWEEN '1985-01-01' AND '1992-12-31'
  AND (gender = 'F' OR last_name LIKE 'S%')
  AND first_name NOT LIKE '%son%';

/*Question 7: Count the number of records in dept_manager where from_date is after '1990-
01-01' and (dept_no = 'd001' or dept_no = 'd005' or dept_no = 'd008') and to_date is not
NULL. Name the result column MANAGER_COUNT.*/
SELECT COUNT(*) AS MANAGER_COUNT
FROM dept_manager
WHERE from_date > '1990-01-01'
  AND (dept_no = 'd001' OR dept_no = 'd005' OR dept_no = 'd008')
  AND to_date IS NOT NULL;

/*Question 8: Display the concatenation of dept_no and dept_name with a colon and space in
between (format: [dept_no]: [dept_name]) as DEPARTMENT_INFO from departments table
where dept_name contains 'Eng' or 'Research' and dept_no is in ('d001', 'd003', 'd005',
'd007').*/
SELECT CONCAT('[', dept_no, ']: [', dept_name, ']') AS DEPARTMENT_INFO
FROM departments
WHERE (dept_name LIKE '%Eng%' OR dept_name LIKE '%Research%')
  AND dept_no IN ('d001', 'd003', 'd005', 'd007');

/*Question 9: From salaries table, find the minimum salary as MIN_SAL, maximum salary as
MAX_SAL, and the difference between them as SALARY_RANGE for salaries where
emp_no is between 10001 and 10100 and salary is not NULL and from_date is after '1995-01-01'.*/
SELECT MIN(salary)               AS MIN_SAL,
       MAX(salary)               AS MAX_SAL,
       MAX(salary) - MIN(salary) AS SALARY_RANGE
FROM salaries
WHERE emp_no BETWEEN 10001 AND 10100
  AND salary IS NOT NULL
  AND from_date > '1995-01-01';

/*Display emp_no, title, and a concatenated column TITLE_PERIOD in the
format [title] from [from_date] from titles table where from_date is between '1985-01-01' and
'1992-12-31' and (title like '%Engineer%' or title like '%Staff%') and to_date is NULL or
to_date = '9999-01-01'.*/
SELECT emp_no,
       title,
       CONCAT(title, ' from ', from_date) AS TITLE_PERIOD
FROM titles
WHERE from_date BETWEEN '1985-01-01' AND '1992-12-31'
  AND (title LIKE '%Engineer%' OR title LIKE '%Staff%')
  AND (to_date IS NULL OR to_date = '9999-01-01');

/*Question 11: Count the number of female employees (gender = 'F') whose first_name starts
with 'M' or 'P' and whose hire_date is before '1990-01-01' or after '2000-01-01'. Use
    COALESCE to treat NULL first_name as empty string. Name the result
    FEMALE_SPECIFIC_COUNT.*/
SELECT COUNT(*) AS FEMALE_SPECIFIC_COUNT
FROM employees
WHERE gender = 'F'
  AND (COALESCE(first_name, '') LIKE 'M%'
    OR COALESCE(first_name, '') LIKE 'P%')
  AND (hire_date < '1990-01-01'
    OR hire_date > '2000-01-01');

/*Question 12: Display full name and hire date showing only month and year in format 'Month
YYYY'.
Hint: Use TO_CHAR(hire_date, 'Month YYYY') – Month shows full month name.*/
SELECT CONCAT(first_name, ' ', last_name) AS FULL_NAME,
       TO_CHAR(hire_date, 'Month YYYY')   AS HIRE_DATE
FROM employees;

/*Question 13: Display hire_date formatted to show only day of week and year: 'Day, Year
YYYY'.
Hint: TO_CHAR(hire_date, 'Day, "Year" YYYY') – use double quotes for literal text.*/
SELECT TO_CHAR(hire_date, 'Day, "Year" YYYY') AS HIRE_DATE_FORMATTED
FROM employees;

/*Question 14:Display employee name, birthdate, and age in years only (without months and
days).
Hint: Use EXTRACT(YEAR FROM AGE(CURRENT_DATE,
birth_date)) or DATE_PART('year', AGE(...)).*/
SELECT CONCAT(first_name, ' ', last_name)               AS FULL_NAME,
       birth_date,
       EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)) AS AGE_YEARS
FROM employees;

/*Question 15: Find employees who are older than 50 years.
Hint: Filter with EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)) > 50.*/
SELECT emp_no,
       first_name,
       last_name,
       birth_date
FROM employees
WHERE EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)) > 50;

/*Question 16: Display all unique first names from the employees table.
Hint: Use SELECT DISTINCT first_name.*/
SELECT DISTINCT first_name
FROM employees;

/*Question 17: Display all unique combinations of first_name and last_name (no duplicate full
names).
Hint: SELECT DISTINCT first_name, last_name.*/
SELECT DISTINCT first_name, last_name
FROM employees;

/*Question 18: Display all employees sorted by first name in ascending order.
Hint: Use SELECT column_name FROM table_name ORDER BY column_name
    ASC (ASC is default, so you can also write just ORDER BY column_name)*/
SELECT *
FROM employees
ORDER BY first_name ASC;

/*Question 19: Display all employees sorted by hire date from newest to oldest.
Hint: Use ORDER BY hire_date DESC for newest first.*/
SELECT *
FROM employees
ORDER BY hire_date DESC;

/*Question 20: Display employee full name, hire date, and age at hire date (how old they
were when hired). Sort by age at hire from youngest to oldest.
Hint: Use AGE(hire_date, birth_date) to calculate age at hire. ORDER BY AGE(hire_date,
birth_date) ASC.
*/
SELECT CONCAT(first_name, ' ', last_name) AS FULL_NAME,
       hire_date,
       AGE(hire_date, birth_date)         AS AGE_AT_HIRE
FROM employees
ORDER BY AGE(hire_date, birth_date) ASC;
