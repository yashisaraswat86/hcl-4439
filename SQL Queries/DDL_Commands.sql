-- CREATE database school_db ;
-- Use school_db ;

##Task 1
-- CREATE TABLE students(
--     student_id INT PRIMARY KEY AUTO_INCREMENT ,
--     student_name VARCHAR(50) NOT NULL ,
--     email VARCHAR(100) UNIQUE,
--     age INT CHECK (age >= 5),
--     city VARCHAR(50) DEFAULT 'Delhi',
--     admission_date DATE
-- );
-- INSERT INTO students
-- (student_name, email, age, city, admission_date)
-- VALUES
-- ('Rahul Sharma', 'rahul@gmail.com', 20, 'Delhi', '2026-07-15'),
-- ('Priya Singh', 'priya@gmail.com', 22, 'Noida', '2026-07-20'),
-- ('Amit Kumar', 'amit@gmail.com', 19, 'Ghaziabad', '2026-08-01'),
-- ('Neha Gupta', 'neha@gmail.com', 21, 'Meerut', '2026-08-05'),
-- ('Karan Verma', 'karan@gmail.com', 23, 'Delhi', '2026-08-10'),
-- ('Sneha Sharma', 'sneha@gmail.com', 18, 'Agra', '2026-08-15'),
-- ('Rohit Yadav', 'rohit@gmail.com', 25, 'Lucknow', '2026-08-20'),
-- ('Anjali Singh', 'anjali@gmail.com', 20, 'Delhi', '2026-09-01'),
-- ('Varun Gupta', 'varun@gmail.com', 24, 'Kanpur', '2026-09-05'),
-- ('Pooja Verma', 'pooja@gmail.com', 19, 'Noida', '2026-09-10');

-- Select* from students ;

-- CREATE TABLE marks (
--     mark_id INT PRIMARY KEY AUTO_INCREMENT,
--     student_id INT,
--     subject VARCHAR(100),
--     score INT,
--     FOREIGN KEY (student_id) REFERENCES students(student_id)
-- );

-- ALTER TABLE students
-- ADD phone VARCHAR(15);
-- ALTER TABLE students
-- RENAME COLUMN city TO hometown;
-- CREATE INDEX idx_student_name
-- ON students(student_name);
-- SHOW INDEX FROM students;

-- INSERT INTO students
-- (student_name, email, age, hometown, admission_date, phone)
-- VALUES
-- ('Arjun Mehta', 'arjun@gmail.com', 21, 'Delhi', '2026-11-25', '9876501234');
-- INSERT INTO marks (student_id, subject, score)
-- VALUES
-- (1, 'Maths', 85),
-- (1, 'Science', 90),
-- (1, 'English', 78),
-- (2, 'Maths', 92),
-- (2, 'Science', 88),
-- (2, 'English', 81),
-- (3, 'Maths', 75),
-- (3, 'Science', 95),
-- (4, 'English', 89),
-- (5, 'Maths', 80);


##Task2
-- UPDATE marks
-- SET score = score + 5
-- WHERE subject = 'Maths';

-- UPDATE students
-- SET age = age + 1
-- WHERE hometown = 'Delhi';

-- DELETE FROM students
-- WHERE student_id = 6;
-- ##delete all marks below 20
-- DELETE FROM marks
-- WHERE score < 20;

