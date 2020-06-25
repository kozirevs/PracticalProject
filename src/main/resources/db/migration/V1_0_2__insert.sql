INSERT INTO vets (firstName, lastName, address, speciality) VALUES
('John', 'Johnson', 'Rīga, Brīvības iela 411-25', 'traumatologist and orthopedic'),
('James', 'Jameson', 'Jūrmala, Turaidas iela 11-68', 'surgeons'),
('Jack', 'Jackson', 'Liepaja, Jūras iela 23-149', 'ophthalmologist'),
('Tom', 'Thomson', 'Jelgava, Teātra iela 162-87', 'laboratory specialist');

INSERT INTO pets (race, dateOfBirth, isVaccinated, ownerName) VALUES
('dog', '2013-08-24', 1, 'Aleksandrs'),
('cat', '2015-11-13', 1, 'Olga'),
('parrot', '2017-02-18', 0, 'Sofija'),
('rabbit', '2019-06-23', 0, 'Marija');

INSERT INTO consults (date, description, vetId, petId) VALUES
('2020-09-25', 'operation', 2, 1),
('2020-10-15', 'make analyzes', 4, 4),
('2020-09-25', 'bandage', 1, 2),
('2020-09-25', 'diagnostics', 3, 3);