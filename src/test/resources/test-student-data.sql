TRUNCATE TABLE students RESTART IDENTITY;

INSERT INTO students(name, email, age, gender)
VALUES('Juan Pérez', 'juan@gmail.com', 30, 'Masculino'),
('María García', 'maria@gmail.com', 25, 'Femenino'),
('Carlos López', 'carlos@gmail.com', 32, 'Masculino'),
('Laura Martínez', 'laura@gmail.com', 28, 'Femenino'),
('Pedro Rodríguez', 'pedro@gmail.com', 32, 'Masculino'),
('Ana Gómez', 'ana@gmail.com', 29, 'Femenino'),
('Luis Fernández', 'luis@gmail.com', 31, 'Masculino'),
('Elena Sánchez', 'elena@gmail.com', 27, 'Femenino'),
('Miguel González', 'miguel@gmail.com', 32, 'Masculino'),
('Sofía Díaz', 'sofia@gmail.com', 26, 'Femenino'),
('Carla Díaz', 'carla.diaz@gmail.com', 35, 'Femenino'),
('Melissa Díaz', 'melissa.diaz@gmail.com', 28, 'Femenino'),
('María Díaz', 'maria.diaz@gmail.com', 29, 'Femenino'),
('Milagros Díaz', 'milagros.diaz@gmail.com', 32, 'Femenino');