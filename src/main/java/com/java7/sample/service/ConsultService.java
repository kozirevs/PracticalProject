package com.java7.sample.service;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.ConsultRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.repository.VetRepository;
import org.h2.util.StringUtils;

import java.util.Date;
import java.util.List;

public class ConsultService {
    private final ConsultRepository consultRepository = new ConsultRepository();
    private final PetRepository petRepository = new PetRepository();
    private final VetRepository vetRepository = new VetRepository();

    public String addConsult(Long vetId, Long petId, Date date, String description) {
        // Pet validator
        if (vetId == null || petId == null || date == null || StringUtils.isNullOrEmpty(description)) {
            return "WRONG DATA, CORRECT INPUT !";
        }
        Vet vet = vetRepository.findById(vetId);
        Pet pet = petRepository.findById(petId);

        if (vet == null || pet == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        //UserFactory :: Factory pattern;
        Consult consult = new Consult();
        consult.setVet(vet);
        consult.setPet(pet);
        consult.setDate(date);
        consult.setDescription(description);

        Long insertConsultId = consultRepository.saveConsult(consult).getConsultId();
        return "Consult WITH ID: " + insertConsultId + " IS ADDED !";
    }

    public List<Consult> viewConsults() {
        return consultRepository.findAllConsults();
    }

    public List<Vet> viewVetsByConsults() {
        return consultRepository.findAllVetsByConsults();
    }

    public List<Pet> viewPetsByConsults() {
        return consultRepository.findAllPetsByConsults();
    }

    public String removeConsult(Long consultId) {
        if (consultId == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        Consult consult = consultRepository.findById(consultId);

        if (consult == null) {
            return "WRONG ID, CORRECT INPUT !";
        }

        consultRepository.deleteConsult(consult);
        return "Consult with ID: " + consultId + " IS DELETED !";
    }

    public String updateConsult(Long consultId, Long vetId, Long petId, Date date, String description) {
        if (consultId == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        Consult consult = consultRepository.findById(consultId);

        if (consult == null) {
            return "WRONG ID, CORRECT INPUT !";
        }

        Vet vet = null;
        if (vetId != null) {
            vet = vetRepository.findById(vetId);
        }
        if (vet != null) {
            consult.setVet(vet);
        }

        Pet pet = null;
        if (petId != null) {
            pet = petRepository.findById(petId);
        }
        if (pet != null) {
            consult.setPet(pet);
        }

        if (date != null) {
            consult.setDate(date);
        }
        if (!StringUtils.isNullOrEmpty(description)) {
            consult.setDescription(description);
        }

        consultRepository.updateConsult(consult);
        return "Consult with ID: " + consultId + " IS UPDATED !";
    }
}
