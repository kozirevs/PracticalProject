package com.java7.sample.service;

import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.service.exception.InputValidationException;
import com.java7.sample.service.factory.PetFactory;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.java7.sample.service.validator.Validator.stringValidation;
import static com.java7.sample.service.validator.Validator.stringValidationAndParseLong;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PetServiceTest {
    private final ModelRepository modelRepository = mock(ModelRepository.class);
    private final PetRepository petRepository = mock(PetRepository.class);
    private final PetFactory petFactory = mock(PetFactory.class);

    private final PetService petService = new PetService(modelRepository, petRepository, petFactory);

    @Test
    public void addPetShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String anyString = "A";
        LocalDate todayDate = LocalDate.now();
        Boolean anyBoolean = false;
        Long petId = 22L;
        Pet pet = new Pet();
        String expectedMsg = "PET WITH ID: " + petId + " IS ADDED !";

        when(petFactory.createPet(anyString, todayDate, anyBoolean, anyString)).thenReturn(pet);
        when(modelRepository.saveModel(pet)).thenReturn(petId);

        String result = petService.addPet(anyString, todayDate, anyBoolean, anyString);

        assertEquals(expectedMsg, result);
    }

    @Test
    public void addPetShouldReturnMessageIfValidationFailed() throws InputValidationException {
        String anyString = "A";
        LocalDate todayDate = LocalDate.now();
        Boolean anyBoolean = false;
        String expectedMsg = "WRONG INPUT, PLEASE CORRECT";

        when(petFactory.createPet(anyString, todayDate, anyBoolean, anyString))
                .thenThrow(new InputValidationException(expectedMsg));

        String result = petService.addPet(anyString, todayDate, anyBoolean, anyString);

        assertEquals(expectedMsg, result);
    }

    @Test
    public void removePetShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String id = " 1   ";
        Long validId = stringValidationAndParseLong(stringValidation(id));
        Pet pet = new Pet();
        List<Pet> petList = new ArrayList<>();
        String expectedMsg = "PET WITH ID: " + validId + " IS DELETED !";

        when(petRepository.findById(validId)).thenReturn(pet);
        when(petRepository.findAllPetsByConsults()).thenReturn(petList);

        String result = petService.removePet(id);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).deleteModel(pet);
    }

    @Test
    public void updateVetShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String id = " 1   ";
        String anyString = "A";
        LocalDate todayDate = LocalDate.now();
        Boolean anyBoolean = false;
        Long validId = stringValidationAndParseLong(stringValidation(id));
        Pet pet = new Pet();
        Pet updatedPet = new Pet();
        String expectedMsg = "PET WITH ID: " + validId + " IS UPDATED !";

        when(petRepository.findById(validId)).thenReturn(pet);
        when(petFactory.createUpdatedPet(pet, anyString, todayDate, anyBoolean, anyString)).thenReturn(updatedPet);

        String result = petService.updatePet(id, anyString, todayDate, anyBoolean, anyString);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).updateModel(pet);
    }
}
