package com.java7.sample.service.validator;

import com.java7.sample.model.Model;
import com.java7.sample.model.Vet;
import com.java7.sample.service.exception.InputValidationException;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.java7.sample.service.validator.Validator.*;
import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void stringValidationShouldReturnTrimmedStringIfInputIsValidString() throws InputValidationException {
        String anyStr = " aaa  ";
        String result = stringValidation(anyStr);
        String expected = "aaa";

        assertEquals(expected, result);
    }

    @Test(expected = InputValidationException.class)
    public void stringValidationShouldReturnExceptionMessageIfInputIsNull() throws InputValidationException {
        String anyStr = null;
        String expectedMsg = "EMPTY INPUT, PLEASE CORRECT !";
        try {
            stringValidation(anyStr);
        } catch (InputValidationException e) {
            assertEquals(expectedMsg, e.getMessage());
            throw e;
        }
    }

    @Test(expected = InputValidationException.class)
    public void stringValidationShouldReturnExceptionMessageIfInputIsEmptyString() throws InputValidationException {
        String anyStr = "";
        String expectedMsg = "EMPTY INPUT, PLEASE CORRECT !";
        try {
            stringValidation(anyStr);
        } catch (InputValidationException e) {
            assertEquals(expectedMsg, e.getMessage());
            throw e;
        }
    }

    @Test(expected = InputValidationException.class)
    public void dateValidationShouldReturnExceptionMessageIfInputIsNull() throws InputValidationException {
        LocalDate anyDate = null;
        String expectedMsg = "WRONG OR EMPTY INPUT, PLEASE CORRECT !";
        try {
            dateValidation(anyDate);
        } catch (InputValidationException e) {
            assertEquals(expectedMsg, e.getMessage());
            throw e;
        }
    }

    @Test
    public void longValidationShouldReturnLongIfInputIsValidNumber() throws InputValidationException {
        String anyNumber = "4";
        Long result = stringValidationAndParseLong(anyNumber);
        Long expected = 4L;

        assertEquals(expected, result);
    }

    @Test(expected = InputValidationException.class)
    public void longValidationShouldReturnExceptionMessageIfInputIsNotNumber() throws InputValidationException {
        String notNumber = " a4 ";
        String expectedMsg = "NOT NUMBER INPUT, PLEASE CORRECT !";
        try {
            stringValidationAndParseLong(notNumber);
        } catch (InputValidationException e) {
            assertEquals(expectedMsg, e.getMessage());
            throw e;
        }
    }

    @Test(expected = InputValidationException.class)
    public void modelValidationShouldReturnExceptionMessageIfInputIsNull() throws InputValidationException {
        Model model = null;
        String expectedMsg = "WRONG ID, CORRECT INPUT !";
        try {
            modelValidation(model);
        } catch (InputValidationException e) {
            assertEquals(expectedMsg, e.getMessage());
            throw e;
        }
    }

    @Test(expected = InputValidationException.class)
    public void modelHasConsultValidationShouldReturnExceptionMessageIfInputIsNull() throws InputValidationException {
        Model model = new Vet();
        List<Model> list = new ArrayList();
        list.add(model);
        String expectedMsg = "THE VET HAS CONSULT YOU CAN'T DELETE IT !";
        try {
            modelHasConsultValidation(list, model);
        } catch (InputValidationException e) {
            assertEquals(expectedMsg, e.getMessage());
            throw e;
        }
    }
}