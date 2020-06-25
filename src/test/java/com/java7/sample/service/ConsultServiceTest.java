package com.java7.sample.service;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.ConsultRepository;
import com.java7.sample.repository.ModelRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.exception.InputValidationException;
import com.java7.sample.service.factory.ConsultFactory;
import org.junit.Test;

import java.time.LocalDate;

import static com.java7.sample.service.validator.Validator.stringValidation;
import static com.java7.sample.service.validator.Validator.stringValidationAndParseLong;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ConsultServiceTest {
    private final ModelRepository modelRepository = mock(ModelRepository.class);
    private final ConsultRepository consultRepository = mock(ConsultRepository.class);
    private final VetRepository vetRepository = mock(VetRepository.class);
    private final PetRepository petRepository = mock(PetRepository.class);
    private final ConsultFactory consultFactory = mock(ConsultFactory.class);

    private final ConsultService consultService = new ConsultService(modelRepository,
            consultRepository, petRepository, vetRepository, consultFactory);

    @Test
    public void addConsultShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String anyString = "A";
        LocalDate todayDate = LocalDate.now();
        Long consultId = 22L;
        Vet vet = new Vet();
        Pet pet = new Pet();
        Consult consult = new Consult();
        consult.setVet(vet);
        consult.setPet(pet);
        String expectedMsg = "CONSULT WITH ID: " + consultId + " IS ADDED !";

        when(consultFactory.createConsult(anyString, anyString, todayDate, anyString)).thenReturn(consult);
        when(vetRepository.findById(consult.getVet().getId())).thenReturn(vet);
        when(petRepository.findById(consult.getPet().getId())).thenReturn(pet);
        when(modelRepository.saveModel(consult)).thenReturn(consultId);

        String result = consultService.addConsult(anyString, anyString, todayDate, anyString);

        assertEquals(expectedMsg, result);
    }

    @Test
    public void addConsultShouldReturnMessageIfValidationFailed() throws InputValidationException {
        String anyString = "A";
        LocalDate todayDate = LocalDate.now();
        String expectedMsg = "WRONG INPUT, PLEASE CORRECT";

        when(consultFactory.createConsult(anyString, anyString, todayDate, anyString))
                .thenThrow(new InputValidationException(expectedMsg));

        String result = consultService.addConsult(anyString, anyString, todayDate, anyString);

        assertEquals(expectedMsg, result);
    }

    @Test
    public void removeConsultShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String id = " 1   ";
        Long validId = stringValidationAndParseLong(stringValidation(id));
        Consult consult = new Consult();
        String expectedMsg = "CONSULT WITH ID: " + validId + " IS DELETED !";

        when(consultRepository.findById(validId)).thenReturn(consult);

        String result = consultService.removeConsult(id);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).deleteModel(consult);
    }

    @Test
    public void updateConsultShouldReturnCorrectMessageIfInputIsCorrect() throws InputValidationException {
        String id = " 1   ";
        String anyString = "A";
        LocalDate todayDate = LocalDate.now();
        Long validId = stringValidationAndParseLong(stringValidation(id));
        Vet vet = new Vet();
        Pet pet = new Pet();
        Consult consult = new Consult();
        Consult updatedConsult = new Consult();
        updatedConsult.setVet(vet);
        updatedConsult.setPet(pet);
        String expectedMsg = "CONSULT WITH ID: " + validId + " IS UPDATED !";

        when(consultRepository.findById(validId)).thenReturn(consult);
        when(consultFactory.createUpdatedConsult(consult, anyString, anyString, todayDate, anyString))
                .thenReturn(updatedConsult);
        when(vetRepository.findById(updatedConsult.getVet().getId())).thenReturn(vet);
        when(petRepository.findById(updatedConsult.getPet().getId())).thenReturn(pet);

        String result = consultService.updateConsult(id, anyString, anyString, todayDate, anyString);

        assertEquals(expectedMsg, result);
        verify(modelRepository, times(1)).updateModel(updatedConsult);
    }

}