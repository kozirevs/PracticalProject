package com.java7.sample.service;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.ConsultRepository;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.exception.InputValidationException;
import com.java7.sample.service.factory.ConsultFactory;

import java.time.LocalDate;
import java.util.List;

import static com.java7.sample.service.validator.Validator.*;

public class ConsultService {
    private final ModelRepository modelRepository;
    private final ConsultRepository consultRepository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final ConsultFactory consultFactory;

    public ConsultService(ModelRepository modelRepository, ConsultRepository consultRepository,
                          PetRepository petRepository, VetRepository vetRepository, ConsultFactory consultFactory) {
        this.modelRepository = modelRepository;
        this.consultRepository = consultRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.consultFactory = consultFactory;
    }

    public String addConsult(String vetId, String petId, LocalDate date, String description) {
        try {
            Consult validConsult = consultFactory.createConsult(vetId, petId, date, description);
            Vet validVet = (Vet) modelValidation(vetRepository.findById(validConsult.getVet().getId()));
            Pet validPet = (Pet) modelValidation(petRepository.findById(validConsult.getPet().getId()));
            validConsult.setVet(validVet);
            validConsult.setPet(validPet);
            Long insertId = modelRepository.saveModel(validConsult);
            return "CONSULT WITH ID: " + insertId + " IS ADDED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public List<Object[]> viewConsults() {
        return consultRepository.findAllConsultsWithVetAndPets();
    }

    public String removeConsult(String id) {
        try {
            Long validId = stringValidationAndParseLong(stringValidation(id));
            Consult validConsult = (Consult) modelValidation(consultRepository.findById(validId));
            modelRepository.deleteModel(validConsult);
            return "CONSULT WITH ID: " + validId + " IS DELETED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String updateConsult(String id, String vetId, String petId, LocalDate date, String description) {
        try {
            Long validId = stringValidationAndParseLong(stringValidation(id));
            Consult notUpdatedConsult = (Consult) modelValidation(consultRepository.findById(validId));
            Consult updatedConsult = consultFactory
                    .createUpdatedConsult(notUpdatedConsult, vetId, petId, date, description);
            Vet validVet = (Vet) modelValidation(vetRepository.findById(updatedConsult.getVet().getId()));
            Pet validPet = (Pet) modelValidation(petRepository.findById(updatedConsult.getPet().getId()));
            updatedConsult.setVet(validVet);
            updatedConsult.setPet(validPet);
            modelRepository.updateModel(updatedConsult);
            return "CONSULT WITH ID: " + validId + " IS UPDATED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
