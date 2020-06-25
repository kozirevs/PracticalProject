package com.java7.sample.service.factory;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.service.exception.InputValidationException;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

public class ConsultFactoryTest {

    ConsultFactory consultFactory = new ConsultFactory();

    @Test
    public void createUpdatedConsultShouldUseOldVetIfPassedVetIdIsNull() throws InputValidationException {
        String vetId = null;
        String petId = " 1 ";
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Pet pet = new Pet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setPet(pet);
        expectedConsult.setDate(date);
        expectedConsult.setDescription(anyString);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, anyString);

        assertEquals(expectedConsult, resultConsult);
    }

    @Test
    public void createUpdatedConsultShouldUseOldVetIfPassedVetIdIsEmpty() throws InputValidationException {
        String vetId = " ";
        String petId = " 1 ";
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Pet pet = new Pet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setPet(pet);
        expectedConsult.setDate(date);
        expectedConsult.setDescription(anyString);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, anyString);

        assertEquals(expectedConsult, resultConsult);
    }

    @Test
    public void createUpdatedConsultShouldUseOldPetIfPassedPetIdIsNull() throws InputValidationException {
        String vetId = " 1 ";
        String petId = null;
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Vet vet = new Vet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setVet(vet);
        expectedConsult.setDate(date);
        expectedConsult.setDescription(anyString);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, anyString);

        assertEquals(expectedConsult, resultConsult);
    }

    @Test
    public void createUpdatedConsultShouldUseOldPetIfPassedPetIdIsEmpty() throws InputValidationException {
        String vetId = " 1 ";
        String petId = " ";
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Vet vet = new Vet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setVet(vet);
        expectedConsult.setDate(date);
        expectedConsult.setDescription(anyString);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, anyString);

        assertEquals(expectedConsult, resultConsult);
    }

    @Test
    public void createUpdatedConsultShouldUseOldDateIfPassedDateIdIsNull() throws InputValidationException {
        String vetId = " 1 ";
        String petId = " 1 ";
        String anyString = "A";
        LocalDate localDate = null;
        Vet vet = new Vet();
        Pet pet = new Pet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setVet(vet);
        expectedConsult.setPet(pet);
        expectedConsult.setDescription(anyString);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, anyString);

        assertEquals(expectedConsult, resultConsult);
    }

    @Test
    public void createUpdatedConsultShouldUseOldDescriptionIfPassedDescriptionIsNull() throws InputValidationException {
        String vetId = " 1 ";
        String petId = " 1 ";
        String description = null;
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Vet vet = new Vet();
        Pet pet = new Pet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setVet(vet);
        expectedConsult.setPet(pet);
        expectedConsult.setDate(date);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, description);

        assertEquals(expectedConsult, resultConsult);
    }

    @Test
    public void createUpdatedConsultShouldUseOldDescriptionIfPassedDescriptionIsEmpty() throws InputValidationException {
        String vetId = " 1 ";
        String petId = " 1 ";
        String description = "  ";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Vet vet = new Vet();
        Pet pet = new Pet();
        Consult consult = new Consult();
        Consult expectedConsult = new Consult();
        expectedConsult.setVet(vet);
        expectedConsult.setPet(pet);
        expectedConsult.setDate(date);

        Consult resultConsult = consultFactory.createUpdatedConsult(consult, vetId, petId, localDate, description);

        assertEquals(expectedConsult, resultConsult);
    }
}