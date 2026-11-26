
-- ============================================
-- DDL TASKS
-- ============================================

-- TASK 1: CREATE THE STRUCTURE

-- 1. Create database and select it
CREATE DATABASE school_db;

USE school_db;

-- 2. Create students table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    age INT CHECK (age >= 5),
    city VARCHAR(50) DEFAULT 'Delhi',
    admission_date DATE
);


-- ============================================
-- TASK 2: MODIFY THE STRUCTURE
-- ============================================

-- 1. Create marks table with foreign key
CREATE TABLE marks (
    mark_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject VARCHAR(30),
    score INT,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

-- 2. Add phone column to students
ALTER TABLE students
ADD phone INT;

-- 3. Change phone datatype to VARCHAR(15)
ALTER TABLE students
MODIFY phone VARCHAR(15);

-- 4. Rename city column to hometown
ALTER TABLE students
RENAME COLUMN city TO hometown;

-- 5. Create an index on name
CREATE INDEX idx_student_name
ON students(name);

-- 6. Create a copy of the structure as students_backup
CREATE TABLE students_backup LIKE students;

-- Drop students_backup table
DROP TABLE students_backup;


-- ============================================
-- END OF DDL TASKS
-- ============================================

-- Verify table structures
DESC students;
DESC marks;

-- Show all tables
SHOW TABLES;
