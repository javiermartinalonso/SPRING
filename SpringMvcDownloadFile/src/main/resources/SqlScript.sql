CREATE DATABASE IF NOT EXISTS filedownload;

USE filedownload;

CREATE TABLE exam_result (
	student_id INTEGER NOT NULL,
	student_name VARCHAR(30) NOT NULL,
	student_dob DATE NOT NULL,
	student_percentage  double NOT NULL
);

INSERT INTO exam_result(student_id, student_name, student_dob, student_percentage) VALUES (101, 'Harry Potter', '1993-02-01', 92);
INSERT INTO exam_result(student_id, student_name, student_dob, student_percentage) VALUES (102, 'Java Code Geek', '1987-02-03', 62);
INSERT INTO exam_result(student_id, student_name, student_dob, student_percentage) VALUES (103, 'Hermione Granger', '1985-02-01', 76);
INSERT INTO exam_result(student_id, student_name, student_dob, student_percentage) VALUES (104, 'Lucifer Morningstar', '1965-02-01', 83);

DESC exam_result;

SELECT * FROM exam_result;