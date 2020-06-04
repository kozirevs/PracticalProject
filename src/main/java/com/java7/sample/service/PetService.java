package com.java7.sample.service;

import com.java7.sample.model.Pet;
import com.java7.sample.repository.PetRepository;
import org.h2.util.StringUtils;

import java.util.Date;
import java.util.List;

public class PetService {
    private final PetRepository petRepository = new PetRepository();

    public String addPet(String race, Date dateOfBirth, String isVaccinated, String ownerName) {
        // Pet validator
        if (StringUtils.isNullOrEmpty(race) || dateOfBirth == null
                || StringUtils.isNullOrEmpty(isVaccinated) || StringUtils.isNullOrEmpty(ownerName)) {

            return "WRONG DATA, CORRECT INPUT !";

        } else {
            //UserFactory :: Factory pattern;
            Pet pet = new Pet();
            pet.setRace(race);
            pet.setDateOfBirth(dateOfBirth);
            pet.setIsVaccinated(isVaccinated);
            pet.setOwnerName(ownerName);

            Long insertPetId = petRepository.savePet(pet).getPetId();
            return "PET WITH ID: " + insertPetId + " IS ADDED !";
        }
    }

    public List<Pet> viewPets() {
        return petRepository.findAllPets();
    }

    public String removePet(Long petId) {
        if (petId == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        Pet pet = petRepository.findById(petId);

        if (pet == null) {
            return "WRONG ID, CORRECT INPUT !";
        }

        petRepository.deletePet(pet);
        return "PET with ID: " + petId + " IS DELETED !";
    }

    public String updatePet(Long petId, String race, Date dateOfBirth, String isVaccinated, String ownerName) {
        if (petId == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        Pet pet = petRepository.findById(petId);

        if (pet == null) {
            return "WRONG ID, CORRECT INPUT !";
        }

        if (!StringUtils.isNullOrEmpty(race)) {
            pet.setRace(race);
        }
        if (dateOfBirth != null) {
            pet.setDateOfBirth(dateOfBirth);
        }
        if (!StringUtils.isNullOrEmpty(isVaccinated)) {
            pet.setIsVaccinated(isVaccinated);
        }
        if (!StringUtils.isNullOrEmpty(ownerName)) {
            pet.setOwnerName(ownerName);
        }

        petRepository.updatePet(pet);
        return "PET with ID: " + petId + " IS UPDATED !";
    }
}
