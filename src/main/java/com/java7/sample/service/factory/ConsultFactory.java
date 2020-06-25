package com.java7.sample.service.factory;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.service.exception.InputValidationException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.java7.sample.service.validator.Validator.*;

public class ConsultFactory {

    public Consult createConsult(String vetId, String petId, LocalDate date, String description)
            throws InputValidationException {

        Long validVetId = stringValidationAndParseLong(stringValidation(vetId));
        Long validPetId = stringValidationAndParseLong(stringValidation(petId));
        Date validDate = dateValidation(date);
        String validDescription = stringValidation(description);

        Vet vet = new Vet();
        vet.setId(validVetId);

        Pet pet = new Pet();
        pet.setId(validPetId);

        Consult consult = new Consult();
        consult.setVet(vet);
        consult.setPet(pet);
        consult.setDate(validDate);
        consult.setDescription(validDescription);

        return consult;
    }

    public Consult createUpdatedConsult(Consult consult, String vetId, String petId, LocalDate localDate,
                                        String description) throws InputValidationException {
        if (vetId != null) {
            String trimmedVetId = vetId.trim();
            if (!trimmedVetId.isEmpty()) {
                Long validVetId = stringValidationAndParseLong(trimmedVetId);

                Vet vet = new Vet();
                vet.setId(validVetId);

                consult.setVet(vet);
            }
        }
        if (petId != null) {
            String trimmedPetId = petId.trim();
            if (!trimmedPetId.isEmpty()) {
                Long validPetId = stringValidationAndParseLong(trimmedPetId);

                Pet pet = new Pet();
                pet.setId(validPetId);

                consult.setPet(pet);
            }
        }
        if (localDate != null) {
            Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            consult.setDate(date);
        }
        if (description != null) {
            String trimmedDescription = description.trim();
            if (!trimmedDescription.isEmpty()) {
                consult.setDescription(trimmedDescription);
            }
        }
        return consult;
    }
}
