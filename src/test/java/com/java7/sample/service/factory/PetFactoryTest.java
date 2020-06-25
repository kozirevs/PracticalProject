package com.java7.sample.service.factory;

import com.java7.sample.model.Pet;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;

public class PetFactoryTest {

    PetFactory petFactory = new PetFactory();

    @Test
    public void createUpdatedPetShouldUseOldRaceIfPassedRaceIsNull() {
        String race = null;
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Boolean anyBoolean = false;
        Pet pet = new Pet();
        Pet expectedPet = new Pet();
        expectedPet.setDateOfBirth(date);
        expectedPet.setIsVaccinated(anyBoolean);
        expectedPet.setOwnerName(anyString);

        Pet resultPet = petFactory.createUpdatedPet(pet, race, localDate, anyBoolean, anyString);

        assertEquals(expectedPet, resultPet);
    }

    @Test
    public void createUpdatedPetShouldUseOldRaceIfPassedRaceIsEmpty() {
        String race = " ";
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Boolean anyBoolean = false;
        Pet pet = new Pet();
        Pet expectedPet = new Pet();
        expectedPet.setDateOfBirth(date);
        expectedPet.setIsVaccinated(anyBoolean);
        expectedPet.setOwnerName(anyString);

        Pet resultPet = petFactory.createUpdatedPet(pet, race, localDate, anyBoolean, anyString);

        assertEquals(expectedPet, resultPet);
    }

    @Test
    public void createUpdatedPetShouldUseOldDateOfBirthIfPassedDateOfBirthIsNull() {
        String anyString = "A";
        LocalDate localDate = null;
        Boolean anyBoolean = false;
        Pet pet = new Pet();
        Pet expectedPet = new Pet();
        expectedPet.setRace(anyString);
        expectedPet.setIsVaccinated(anyBoolean);
        expectedPet.setOwnerName(anyString);

        Pet resultPet = petFactory.createUpdatedPet(pet, anyString, localDate, anyBoolean, anyString);

        assertEquals(expectedPet, resultPet);
    }

    @Test
    public void createUpdatedPetShouldUseOldOwnerNameIfPassedOwnerNameIsNull() {
        String ownerName = null;
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Boolean anyBoolean = false;
        Pet pet = new Pet();
        Pet expectedPet = new Pet();
        expectedPet.setRace(anyString);
        expectedPet.setDateOfBirth(date);
        expectedPet.setIsVaccinated(anyBoolean);

        Pet resultPet = petFactory.createUpdatedPet(pet, anyString, localDate, anyBoolean, ownerName);

        assertEquals(expectedPet, resultPet);
    }

    @Test
    public void createUpdatedPetShouldUseOldOwnerNameIfPassedOwnerNameIsEmpty() {
        String ownerName = " ";
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Boolean anyBoolean = false;
        Pet pet = new Pet();
        Pet expectedPet = new Pet();
        expectedPet.setRace(anyString);
        expectedPet.setDateOfBirth(date);
        expectedPet.setIsVaccinated(anyBoolean);

        Pet resultPet = petFactory.createUpdatedPet(pet, anyString, localDate, anyBoolean, ownerName);

        assertEquals(expectedPet, resultPet);
    }

    @Test
    public void createUpdatedPetShouldReturnFullyUpdatedPetIfAllFieldsAreProvided() {
        String anyString = "A";
        LocalDate localDate = LocalDate.now();
        Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        Boolean anyBoolean = false;
        Pet pet = new Pet();
        Pet expectedPet = new Pet();
        expectedPet.setRace(anyString);
        expectedPet.setDateOfBirth(date);
        expectedPet.setIsVaccinated(anyBoolean);
        expectedPet.setOwnerName(anyString);

        Pet resultPet = petFactory.createUpdatedPet(pet, anyString, localDate, anyBoolean, anyString);

        assertEquals(expectedPet, resultPet);
    }
}