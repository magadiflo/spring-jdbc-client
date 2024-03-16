TRUNCATE TABLE students RESTART IDENTITY;

INSERT INTO students (name, email, age, gender)
VALUES('Pro', 'pro@knf.com', 60, 'Male'),
('Alpha', 'alpha@knf.com', 50, 'Male'),
('Beta', 'beta@knf.com', 40, 'Female'),
('Gama', 'gama@knf.com', 30, 'Male'),
('Pekka', 'pekka@knf.com', 20, 'Female'),
('Noob', 'noob@knf.com', 10, 'Male'),
('Noob2', 'noob2@knf.com', 5, 'Male'),
('Noob3', 'noob3@knf.com', 5, 'Male');