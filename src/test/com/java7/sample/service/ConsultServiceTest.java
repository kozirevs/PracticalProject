package com.java7.sample.service;

import com.java7.sample.model.Consult;
import com.java7.sample.model.Pet;
import com.java7.sample.model.Vet;
import com.java7.sample.repository.ConsultRepository;
import com.java7.sample.repository.PetRepository;
import com.java7.sample.repository.VetRepository;
import com.java7.sample.service.factory.ConsultFactory;
import com.java7.sample.service.validator.ConsultServiceValidator;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsultServiceTest {


    private ConsultRepository consultRepository = mock(ConsultRepository.class);
    private PetRepository petRepository = mock(PetRepository.class);
    private VetRepository vetRepository = mock(VetRepository.class);
    private ConsultFactory consultFactory = mock(ConsultFactory.class);

    private ConsultService consultService
            = new ConsultService(consultRepository, petRepository,
            vetRepository, consultFactory,
            mock(ConsultServiceValidator.class));

    @Test
    public void addConsult() {
        Pet pet = new Pet();
        Vet vet = new Vet();
        Date date = new Date();
        Consult consult = new Consult();
        Consult savedConsult = new Consult();
        savedConsult.setConsultId(22L);
                //"Consult WITH ID: " + insertConsultId + " IS ADDED !";

        when(petRepository.findById(1L)).thenReturn(pet);
        when(vetRepository.findById(1L)).thenReturn(vet);

        when(consultFactory.createConsult(
                vet,pet,date,"description"
        )).thenReturn(consult);

        when(consultRepository.saveConsult(consult)).thenReturn(savedConsult);

        assertEquals("Consult WITH ID: 22 IS ADDED !",
                consultService.addConsult(1L, 1L, date,
                "description"));
    }
}