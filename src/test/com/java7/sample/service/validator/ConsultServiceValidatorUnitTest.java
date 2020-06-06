package com.java7.sample.service.validator;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ConsultServiceValidatorUnitTest {
    ConsultServiceValidator consultServiceValidator = new ConsultServiceValidator();

    @Test
    public void testCheckDataCorrect() {
        Long vetId = 1L;
        Long petId = 1L;
        Date date = new Date();
        String description = "Q";

        assertNull(
                consultServiceValidator
                        .checkDataCorrect(vetId,petId,date,description));

        assertEquals("WRONG DATA, CORRECT INPUT !",
                consultServiceValidator
                        .checkDataCorrect(null,petId,date,description));

        assertEquals("WRONG DATA, CORRECT INPUT !",
                consultServiceValidator
                        .checkDataCorrect(vetId,null,date,description));

    }
}