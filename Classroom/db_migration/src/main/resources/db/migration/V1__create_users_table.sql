CREATE TABLE students(
                         student_id INT PRIMARY KEY AUTO_INCREMENT ,
                         student_name VARCHAR(50) NOT NULL ,
                         email VARCHAR(100) UNIQUE,
                         age INT CHECK (age >= 5),
                         city VARCHAR(50) DEFAULT 'Delhi',
                         admission_date DATE
);