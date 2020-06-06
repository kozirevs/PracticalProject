package com.java7.sample.service.factory;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;

import java.util.Date;

public class ConsultFactory {
    public Consult createConsult(Vet vet, Pet pet, Date date, String description){
        Consult consult = new Consult();
        consult.setVet(vet);
        consult.setPet(pet);
        consult.setDate(date);
        consult.setDescription(description);
        return consult;
    }


}
