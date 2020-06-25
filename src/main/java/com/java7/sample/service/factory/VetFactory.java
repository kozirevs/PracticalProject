package com.java7.sample.service.factory;

import com.java7.sample.model.Vet;
import com.java7.sample.service.exception.InputValidationException;

import static com.java7.sample.service.validator.Validator.*;

public class VetFactory {

    public Vet createVet(String firstName, String lastName, String address, String speciality)
            throws InputValidationException {

        String validFirstName = stringValidation(firstName);
        String validLastName = stringValidation(lastName);
        String validAddress = stringValidation(address);
        String validSpeciality = stringValidation(speciality);

        Vet vet = new Vet();
        vet.setFirstName(validFirstName);
        vet.setLastName(validLastName);
        vet.setAddress(validAddress);
        vet.setSpeciality(validSpeciality);

        return vet;
    }

    public Vet createUpdatedVet(Vet vet, String firstName, String lastName, String address, String speciality) {
        if (firstName != null) {
            String trimmedFirstName = firstName.trim();
            if (!trimmedFirstName.isEmpty()) {
                vet.setFirstName(trimmedFirstName);
            }
        }
        if (lastName != null) {
            String trimmedLastName = lastName.trim();
            if (!trimmedLastName.isEmpty()) {
                vet.setLastName(trimmedLastName);
            }
        }
        if (address != null) {
            String trimmedAddress = address.trim();
            if (!trimmedAddress.isEmpty()) {
                vet.setAddress(trimmedAddress);
            }
        }
        if (speciality != null) {
            String trimmedSpeciality = speciality.trim();
            if (!trimmedSpeciality.isEmpty()) {
                vet.setSpeciality(trimmedSpeciality);
            }
        }
        return vet;
    }
}
