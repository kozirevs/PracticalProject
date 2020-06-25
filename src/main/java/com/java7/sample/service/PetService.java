package com.java7.sample.service;

import com.java7.sample.model.Pet;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.service.exception.InputValidationException;
import com.java7.sample.service.factory.PetFactory;

import java.time.LocalDate;
import java.util.List;

import static com.java7.sample.service.validator.Validator.*;
import static com.java7.sample.service.validator.Validator.modelHasConsultValidation;

public class PetService {
    private final ModelRepository modelRepository;
    private final PetRepository petRepository;
    private final PetFactory petFactory;

    public PetService(ModelRepository modelRepository, PetRepository petRepository, PetFactory petFactory) {
        this.modelRepository = modelRepository;
        this.petRepository = petRepository;
        this.petFactory = petFactory;
    }

    public String addPet(String race, LocalDate dateOfBirth, Boolean isVaccinated, String ownerName) {
        try {
            Pet validPet = petFactory.createPet(race, dateOfBirth, isVaccinated, ownerName);
            Long insertId = modelRepository.saveModel(validPet);
            return "PET WITH ID: " + insertId + " IS ADDED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public List<Pet> viewPets() {
        return petRepository.findAllPets();
    }

    public String removePet(String id) {
        try {
            Long validId = stringValidationAndParseLong(stringValidation(id));
            Pet validPet = (Pet) modelValidation(petRepository.findById(validId));
            List<Pet> petsWhoHaveConsults = petRepository.findAllPetsByConsults();
            modelHasConsultValidation(petsWhoHaveConsults, validPet);
            modelRepository.deleteModel(validPet);
            return "PET WITH ID: " + validId + " IS DELETED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String updatePet(String id, String race, LocalDate dateOfBirth, Boolean isVaccinated, String ownerName) {
        try {
            Long validId = stringValidationAndParseLong(stringValidation(id));
            Pet notUpdatedPet = (Pet) modelValidation(petRepository.findById(validId));
            Pet updatedPet = petFactory.createUpdatedPet(notUpdatedPet, race, dateOfBirth, isVaccinated, ownerName);
            modelRepository.updateModel(updatedPet);
            return "PET WITH ID: " + validId + " IS UPDATED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
