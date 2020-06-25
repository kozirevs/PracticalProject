package com.java7.sample.service.factory;

import com.java7.sample.model.Vet;
import org.junit.Test;

import static org.junit.Assert.*;

public class VetFactoryTest {

    VetFactory vetFactory = new VetFactory();

    @Test
    public void createUpdatedVetShouldUseOldFirstNameIfPassedFirstNameIsNull() {
        String firstName = null;
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setLastName(anyString);
        expectedVet.setAddress(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, firstName, anyString, anyString, anyString);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldFirstNameIfPassedFirstNameIsEmpty() {
        String firstName = " ";
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setLastName(anyString);
        expectedVet.setAddress(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, firstName, anyString, anyString, anyString);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldFLastNameIfPassedLastNameIsNull() {
        String lastName = null;
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setAddress(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, lastName, anyString, anyString);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldLastNameIfPassedLastNameIsEmpty() {
        String lastName = " ";
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setAddress(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, lastName, anyString, anyString);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldAddressIfPassedAddressIsNull() {
        String address = null;
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setLastName(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, anyString, address, anyString);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldAddressIfPassedAddressIsEmpty() {
        String address = " ";
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setLastName(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, anyString, address, anyString);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldSpecialityIfPassedSpecialityIsNull() {
        String speciality = null;
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setLastName(anyString);
        expectedVet.setAddress(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, anyString, anyString, speciality);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldUseOldSpecialityIfPassedSpecialityIsEmpty() {
        String speciality = "   ";
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setLastName(anyString);
        expectedVet.setAddress(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, anyString, anyString, speciality);

        assertEquals(expectedVet, resultVet);
    }

    @Test
    public void createUpdatedVetShouldReturnFullyUpdatedVetIfAllFieldsAreProvided() {
        String anyString = "A";
        Vet vet = new Vet();
        Vet expectedVet = new Vet();
        expectedVet.setFirstName(anyString);
        expectedVet.setLastName(anyString);
        expectedVet.setAddress(anyString);
        expectedVet.setSpeciality(anyString);

        Vet resultVet = vetFactory.createUpdatedVet(vet, anyString, anyString, anyString, anyString);

        assertEquals(expectedVet, resultVet);
    }
}