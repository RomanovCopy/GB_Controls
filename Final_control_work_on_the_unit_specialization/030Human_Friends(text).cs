-- Создаем базу данных
CREATE DATABASE IF NOT EXISTS Human_Friends;
USE Human_Friends;

-- Таблица "Виды"
CREATE TABLE IF NOT EXISTS Species (
    species_id INT PRIMARY KEY AUTO_INCREMENT,
    species_name VARCHAR(50) NOT NULL
);

-- Таблица "Подвиды"
CREATE TABLE IF NOT EXISTS Subspecies (
    subspecies_id INT PRIMARY KEY AUTO_INCREMENT,
    subspecies_name VARCHAR(50) NOT NULL,
    species_id INT,
    FOREIGN KEY (species_id) REFERENCES Species(species_id)
);

-- Таблица "Животные"
CREATE TABLE IF NOT EXISTS Animals (
    animal_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    health_status VARCHAR(50),
    subspecies_id INT,
    FOREIGN KEY (subspecies_id) REFERENCES Subspecies(subspecies_id)
);

-- Примеры данных для таблицы "Виды"
INSERT INTO Species (species_name) VALUES
('Pets'),
('Packanimal');

-- Примеры данных для таблицы "Подвиды"
INSERT INTO Subspecies (subspecies_name, species_id) VALUES
('Dog', 1),
('Cat', 1),
('Horse', 2),
('Camel', 2),
('Donkey', 2);

-- Примеры данных для таблицы "Животные"
INSERT INTO Animals (name, date_of_birth, health_status, subspecies_id) VALUES
('Бобик', '23-01-10', 'Здоров', 1),
('Мурзик', '22-05-25', 'Здоров', 2),
('Шарик', '20-07-14', 'Здоров', 1),
('Рыжик', '21-08-27', 'Здоров', 2),
('Тузик', '19-09-10', 'Здоров', 1),
('Барсик', '23-01-15', 'Здоров', 2),
('Рекс', '19-10-24', 'Здоров', 1),
('Тайсон', '21-07-18', 'Здоров', 3),
('Майкл', '20-11-30', 'Здоров', 4),
('Лео', '15-10-18', 'Здоров', 5),
('Макс', '17-09-20', 'Здоров', 3);

-- Удаляем записи о 'Camel' из таблицы 'Animals'
DELETE FROM Animals WHERE subspecies_id IN (SELECT subspecies_id FROM Subspecies WHERE subspecies_name = 'Camel');

-- Затем удаляем записи о 'Camel' из таблицы 'Subspecies'
DELETE FROM Subspecies WHERE subspecies_name = 'Camel';

-- Обновляем записи 'Horse' на 'Equine' в таблице 'Subspecies'
UPDATE Subspecies SET subspecies_name = 'Equine' WHERE subspecies_name IN ('Horse', 'Donkey');

-- Обновляем записи 'Horse' на 'Equine' в таблице 'Animals'
UPDATE Animals SET subspecies_id = (SELECT subspecies_id FROM Subspecies WHERE subspecies_name = 'Equine') WHERE subspecies_id IN (SELECT subspecies_id FROM Subspecies WHERE subspecies_name IN ('Horse', 'Donkey'));

-- Создаем новую таблицу Animals_1_3_years
CREATE TABLE IF NOT EXISTS Animals_1_3_years (
    animal_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age_months INT,
    health_status VARCHAR(50),
    subspecies_id INT,
    FOREIGN KEY (animal_id) REFERENCES Animals(animal_id),
    FOREIGN KEY (subspecies_id) REFERENCES Subspecies(subspecies_id)
);

-- Вставляем записи в Animals_1_3_years
INSERT INTO Animals_1_3_years (animal_id, name, age_months, health_status, subspecies_id)
SELECT a.animal_id, 
       a.name, 
       TIMESTAMPDIFF(MONTH, a.date_of_birth, CURDATE()) AS age_months,
       a.health_status,
       a.subspecies_id
FROM Animals a
WHERE TIMESTAMPDIFF(MONTH, a.date_of_birth, CURDATE()) BETWEEN 1 AND 36;

-- Создаем новую таблицу All_Animals
CREATE TABLE IF NOT EXISTS All_Animals (
    animal_id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age_months INT,
    health_status VARCHAR(50),
    subspecies_id INT,
    source_table VARCHAR(20),
    FOREIGN KEY (animal_id) REFERENCES Animals(animal_id),
    FOREIGN KEY (subspecies_id) REFERENCES Subspecies(subspecies_id)
);

-- Вставляем записи из таблицы Animals
INSERT INTO All_Animals (animal_id, name, age_months, health_status, subspecies_id, source_table)
SELECT animal_id, name, TIMESTAMPDIFF(MONTH, date_of_birth, CURDATE()) AS age_months, health_status, subspecies_id, 'Animals'
FROM Animals;

-- Вставляем записи из таблицы Animals_1_3_years, исключая дубликаты
INSERT INTO All_Animals (animal_id, name, age_months, health_status, subspecies_id, source_table)
SELECT a1.animal_id, a1.name, a1.age_months, a1.health_status, a1.subspecies_id, 'Animals_1_3_years'
FROM Animals_1_3_years a1
WHERE NOT EXISTS (
    SELECT 1 FROM All_Animals aa
    WHERE aa.animal_id = a1.animal_id
);

-- Просмотрим результат
SELECT * FROM All_Animals;

-- В таблице отсутствует Camel ( animal_Id 9 ) т.к. ранее мы его удалили







