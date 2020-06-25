package com.java7.sample.service;

import com.java7.sample.model.Vet;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.exception.InputValidationException;
import com.java7.sample.service.factory.VetFactory;

import java.util.List;

import static com.java7.sample.service.validator.Validator.*;

public class VetService {
    private final ModelRepository modelRepository;
    private final VetRepository vetRepository;
    private final VetFactory vetFactory;

    public VetService(ModelRepository modelRepository, VetRepository vetRepository, VetFactory vetValidation) {
        this.modelRepository = modelRepository;
        this.vetRepository = vetRepository;
        this.vetFactory = vetValidation;
    }

    public String addVet(String firstName, String lastName, String address, String speciality) {
        try {
            Vet validVet = vetFactory.createVet(firstName, lastName, address, speciality);
            Long insertId = modelRepository.saveModel(validVet);
            return "VET WITH ID: " + insertId + " IS ADDED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public List<Vet> viewVets() {
        return vetRepository.findAllVets();
    }

    public String removeVet(String id) {
        try {
            Long validId = stringValidationAndParseLong(stringValidation(id));
            Vet validVet = (Vet) modelValidation(vetRepository.findById(validId));
            List<Vet> vetsWhoHaveConsults = vetRepository.findAllVetsByConsults();
            modelHasConsultValidation(vetsWhoHaveConsults, validVet);
            modelRepository.deleteModel(validVet);
            return "VET WITH ID: " + validId + " IS DELETED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public String updateVet(String id, String firstName, String lastName, String address, String speciality) {
        try {
            Long validId = stringValidationAndParseLong(stringValidation(id));
            Vet notUpdatedVet = (Vet) modelValidation(vetRepository.findById(validId));
            Vet updatedVet = vetFactory.createUpdatedVet(notUpdatedVet, firstName, lastName, address, speciality);
            modelRepository.updateModel(updatedVet);
            return "VET WITH ID: " + validId + " IS UPDATED !";
        } catch (InputValidationException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
