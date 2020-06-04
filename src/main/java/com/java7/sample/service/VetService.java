package com.java7.sample.service;

import com.java7.sample.model.Vet;
import com.java7.sample.repository.VetRepository;
import org.h2.util.StringUtils;

import java.util.List;

public class VetService {
    private final VetRepository vetRepository = new VetRepository();

    public String addVet(String firstName, String lastName, String address, String speciality) {
        // Vet validator
        if (StringUtils.isNullOrEmpty(firstName) || StringUtils.isNullOrEmpty(lastName)
                || StringUtils.isNullOrEmpty(address) || StringUtils.isNullOrEmpty(speciality)) {

            return "WRONG DATA, CORRECT INPUT !";

        } else {
            //VetFactory :: Factory pattern;
            Vet vet = new Vet();
            vet.setFirstName(firstName);
            vet.setLastName(lastName);
            vet.setAddress(address);
            vet.setSpeciality(speciality);

            Long insertVetId = vetRepository.saveVet(vet).getVetId();
            return "VET WITH ID: " + insertVetId + " IS ADDED !";
        }
    }

    public List<Vet> viewVets() {
        return vetRepository.findAllVets();
    }

    public String removeVet(Long vetId) {
        if (vetId == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        Vet vet = vetRepository.findById(vetId);

        if (vet == null) {
            return "WRONG ID, CORRECT INPUT !";
        }

        vetRepository.deleteVet(vet);
        return "VET with ID: " + vetId + " IS DELETED !";
    }

    public String updateVet(Long vetId, String firstName, String lastName, String address, String speciality) {
        if (vetId == null) {
            return "WRONG ID, CORRECT INPUT !";
        }
        Vet vet = vetRepository.findById(vetId);

        if (vet == null) {
            return "WRONG ID, CORRECT INPUT !";
        }

        if (!StringUtils.isNullOrEmpty(firstName)) {
            vet.setFirstName(firstName);
        }
        if (!StringUtils.isNullOrEmpty(lastName)) {
            vet.setLastName(lastName);
        }
        if (!StringUtils.isNullOrEmpty(address)) {
            vet.setAddress(address);
        }
        if (!StringUtils.isNullOrEmpty(speciality)) {
            vet.setSpeciality(speciality);
        }

        vetRepository.updateVet(vet);
        return "VET with ID: " + vetId + " IS UPDATED !";
    }
}
