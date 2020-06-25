package com.java7.sample.service.validator;

import com.java7.sample.model.Model;
import com.java7.sample.service.exception.InputValidationException;
import org.h2.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Validator {
    public static String stringValidation(String string) throws InputValidationException {
        if (string == null || string.isEmpty()){
            throw new InputValidationException("EMPTY INPUT, PLEASE CORRECT !");
        } else {
            return string.trim();
        }
    }

    public static Date dateValidation(LocalDate localDate) throws InputValidationException {
        if (localDate == null) {
            throw new InputValidationException("WRONG OR EMPTY INPUT, PLEASE CORRECT !");
        } else {
            Instant instant = Instant.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
            return Date.from(instant);
        }
    }

    public static Long stringValidationAndParseLong(String id) throws InputValidationException {
        if (!StringUtils.isNumber(id)) {
            throw new InputValidationException("NOT NUMBER INPUT, PLEASE CORRECT !");
        } else {
            return Long.parseLong(id);
        }
    }

    public static Model modelValidation(Model model) throws InputValidationException {
        if (model == null) {
            throw new InputValidationException("WRONG ID, CORRECT INPUT !");
        } else {
            return model;
        }
    }

    public static void modelHasConsultValidation(List modelList, Model model) throws InputValidationException {
        if (modelList.contains(model)) {
            throw new InputValidationException("THE VET HAS CONSULT YOU CAN'T DELETE IT !");
        }
    }
}
