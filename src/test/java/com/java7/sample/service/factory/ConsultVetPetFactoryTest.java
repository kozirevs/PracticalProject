package com.java7.sample.service.factory;

import com.java7.sample.model.ConsultVetPet;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ConsultVetPetFactoryTest {

    ConsultVetPetFactory consultVetPetFactory = new ConsultVetPetFactory();

    @Test
    public void shouldConvertArrayIntoClass() {
        Long id = 1L;
        Date date = new Date();
        String anyString = "A";

        ConsultVetPet expectedConsultVetPet = new ConsultVetPet();
        expectedConsultVetPet.setId(id);
        expectedConsultVetPet.setDate(date);
        expectedConsultVetPet.setDescription(anyString);
        expectedConsultVetPet.setFirstName(anyString);
        expectedConsultVetPet.setLastName(anyString);
        expectedConsultVetPet.setRace(anyString);
        expectedConsultVetPet.setOwnerName(anyString);

        Object[] array = {id, date, anyString, anyString, anyString, anyString, anyString};

        ConsultVetPet resultConsultVetPet = consultVetPetFactory.createConsultVetPet(array);

        assertEquals(expectedConsultVetPet, resultConsultVetPet);
    }
}