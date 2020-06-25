package com.java7.sample.service.factory;

import com.java7.sample.model.Pet;
import com.java7.sample.service.exception.InputValidationException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.java7.sample.service.validator.Validator.dateValidation;
import static com.java7.sample.service.validator.Validator.stringValidation;

public class PetFactory {

    public Pet createPet(String race, LocalDate dateOfBirth, Boolean isVaccinated, String ownerName)
            throws InputValidationException {

        String validRace = stringValidation(race);
        Date validDateOfBirth = dateValidation(dateOfBirth);
        String validOwnerName = stringValidation(ownerName);

        Pet pet = new Pet();
        pet.setRace(validRace);
        pet.setDateOfBirth(validDateOfBirth);
        pet.setIsVaccinated(isVaccinated);
        pet.setOwnerName(validOwnerName);

        return pet;
    }

    public Pet createUpdatedPet(Pet pet, String race, LocalDate dateOfBirth, Boolean isVaccinated, String ownerName) {

        if (race != null) {
            String trimmedRace = race.trim();
            if (!trimmedRace.isEmpty()) {
                pet.setRace(trimmedRace);
            }
        }
        if (dateOfBirth != null) {
            Instant instant = Instant.from(dateOfBirth.atStartOfDay().atZone(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            pet.setDateOfBirth(date);
        }
        pet.setIsVaccinated(isVaccinated);

        if (ownerName != null) {
            String trimmedOwnerName = ownerName.trim();
            if (!trimmedOwnerName.isEmpty()) {
                pet.setOwnerName(trimmedOwnerName);
            }
        }
        return pet;
    }
}
