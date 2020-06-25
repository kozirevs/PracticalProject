package com.java7.sample.service;

import com.java7.sample.model.Vet;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.exception.InputValidationException;
import com.java7.sample.service.factory.VetFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.java7.sample.service.validator.Validator.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VetServiceTest {
    private final ModelRepository modelRepository = mock(ModelRepository.class);
    private final VetRepository vetRepository = mock(VetRepository.class);
    private final VetFactory vetFactory = mock(VetFactory.class);

    private final VetService vetService = new VetService(modelRepository, vetRepository, vetFactory);


    @Test
    public void addVetShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String anyString = " A";
        Long vetId = 21L;
        Vet vet = new Vet();
        String expectedMsg = "VET WITH ID: " + vetId + " IS ADDED !";

        when(vetFactory.createVet(anyString, anyString, anyString, anyString)).thenReturn(vet);
        when(modelRepository.saveModel(vet)).thenReturn(vetId);

        String result = vetService.addVet(anyString, anyString, anyString, anyString);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).saveModel(vet);
    }

    @Test
    public void addVetShouldReturnMessageIfValidationFailed() throws InputValidationException {
        String anyString = "A";
        String expectedMsg = "ERROR";

        when(vetFactory.createVet(anyString, anyString, anyString, anyString))
                .thenThrow(new InputValidationException(expectedMsg));

        String result = vetService.addVet(anyString, anyString, anyString, anyString);

        assertEquals(expectedMsg, result);
    }

    @Test
    public void removeVetShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String id = " 1   ";
        Long validId = stringValidationAndParseLong(stringValidation(id));
        Vet vet = new Vet();
        List<Vet> vetList = new ArrayList<>();
        String expectedMsg = "VET WITH ID: " + validId + " IS DELETED !";

        when(vetRepository.findById(validId)).thenReturn(vet);
        when(vetRepository.findAllVetsByConsults()).thenReturn(vetList);

        String result = vetService.removeVet(id);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).deleteModel(vet);
    }

    @Test
    public void updateVetShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String id = " 1   ";
        String anyString = "A";
        Long validId = stringValidationAndParseLong(stringValidation(id));
        Vet vet = new Vet();
        Vet updatedVet = new Vet();
        String expectedMsg = "VET WITH ID: " + validId + " IS UPDATED !";

        when(vetRepository.findById(validId)).thenReturn(vet);
        when(vetFactory.createUpdatedVet(vet, anyString, anyString, anyString, anyString)).thenReturn(updatedVet);

        String result = vetService.updateVet(id, anyString, anyString, anyString, anyString);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).updateModel(vet);
    }
}