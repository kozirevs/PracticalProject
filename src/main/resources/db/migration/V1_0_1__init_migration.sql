CREATE TABLE IF NOT EXISTS vets (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    address VARCHAR(200) NOT NULL,
    speciality VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS pets (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    race VARCHAR(50) NOT NULL,
    dateOfBirth DATE NOT NULL,
    isVaccinated TINYINT(1) NOT NULL,
    ownerName VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS consults (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    description VARCHAR(200) NOT NULL,
    vetId INT NOT NULL,
    petId INT NOT NULL,
    CONSTRAINT fk_consult_vet FOREIGN KEY (vetId) REFERENCES vets (id),
    CONSTRAINT fk_consult_pet FOREIGN KEY (petId) REFERENCES pets (id)
);