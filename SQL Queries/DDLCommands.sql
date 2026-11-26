
-- ============================================
-- DDL TASKS
-- DDL stands for Data Definition Language.
-- It is used to create, modify, and delete database structures.
-- ============================================


-- ============================================
-- TASK 1: CREATE THE STRUCTURE
-- ============================================

-- 1. Create a new database named school_db.
-- CREATE DATABASE is used to create a database.
CREATE DATABASE school_db;

-- Select school_db as the current database.
-- All subsequent table operations will be performed inside this database.
USE school_db;


-- 2. Create a table named students.
CREATE TABLE students (

    -- student_id: Unique identifier for every student.
    -- INT: Stores integer values.
    -- AUTO_INCREMENT: Automatically generates the next ID.
    -- PRIMARY KEY: Ensures each student has a unique, non-NULL ID.
    student_id INT AUTO_INCREMENT PRIMARY KEY,

    -- name: Stores the student's name.
    -- VARCHAR(50): Allows up to 50 characters.
    -- NOT NULL: Name cannot be NULL.
    name VARCHAR(50) NOT NULL,

    -- email: Stores the student's email address.
    -- UNIQUE: Prevents duplicate email addresses.
    email VARCHAR(100) UNIQUE,

    -- age: Stores the student's age.
    -- CHECK: Ensures age is at least 5.
    age INT CHECK (age >= 5),

    -- city: Stores the student's city.
    -- DEFAULT 'Delhi': Uses Delhi if no city is provided.
    city VARCHAR(50) DEFAULT 'Delhi',

    -- admission_date: Stores the student's admission date.
    -- DATE: Stores dates in YYYY-MM-DD format.
    admission_date DATE
);


-- ============================================
-- TASK 2: MODIFY THE STRUCTURE
-- ============================================

-- 1. Create a marks table with a foreign key.
CREATE TABLE marks (

    -- Unique identifier for every marks record.
    mark_id INT AUTO_INCREMENT PRIMARY KEY,

    -- Stores the ID of the student whose marks are recorded.
    student_id INT,

    -- Stores the subject name.
    subject VARCHAR(30),

    -- Stores the marks obtained by the student.
    score INT,

    -- FOREIGN KEY establishes a relationship between marks and students.
    -- student_id in marks references student_id in students.
    -- This ensures that a marks record refers to an existing student.
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);


-- 2. Add a phone column to the students table.
-- ALTER TABLE is used to modify an existing table.
-- ADD adds a new column.
ALTER TABLE students
ADD phone INT;


-- 3. Change the datatype of the phone column to VARCHAR(15).
-- MODIFY changes the datatype or definition of an existing column.
-- VARCHAR(15) allows phone numbers containing up to 15 characters.
ALTER TABLE students
MODIFY phone VARCHAR(15);


-- 4. Rename the city column to hometown.
-- RENAME COLUMN changes the name of an existing column.
ALTER TABLE students
RENAME COLUMN city TO hometown;


-- 5. Create an index on the name column.
-- An index helps MySQL find records faster when searching by name.
-- idx_student_name is the name given to this index.
CREATE INDEX idx_student_name
ON students(name);


-- 6. Create a backup table containing the same structure as students.
-- LIKE copies the table structure, including its columns and indexes.
-- It does not copy the existing student records.
CREATE TABLE students_backup LIKE students;


-- Drop the students_backup table.
-- DROP TABLE permanently deletes the table and its structure.
-- Any data stored in this table is also deleted.
DROP TABLE students_backup;


-- ============================================
-- VERIFY TABLE STRUCTURES
-- ============================================

-- DESC displays the structure of the students table.
-- It shows column names, datatypes, keys, NULL settings, etc.
DESC students;

-- Display the structure of the marks table.
DESC marks;


-- ============================================
-- SHOW ALL TABLES
-- ============================================

-- SHOW TABLES displays all tables present in the selected database.
SHOW TABLES;


-- ============================================
-- END OF DDL TASKS
-- ============================================
