package com.java7.sample.service.factory;

import com.java7.sample.model.ConsultVetPet;

import java.util.Date;

public class ConsultVetPetFactory {

    public ConsultVetPet createConsultVetPet(Object[] array) {
        ConsultVetPet cvp = new ConsultVetPet();
        cvp.setId((Long) array[0]);
        cvp.setDate((Date) array[1]);
        cvp.setDescription((String) array[2]);
        cvp.setFirstName((String) array[3]);
        cvp.setLastName((String) array[4]);
        cvp.setRace((String) array[5]);
        cvp.setOwnerName((String) array[6]);
        return cvp;
    }
}
