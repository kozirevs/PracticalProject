package com.java7.sample.service.validator;

import org.h2.util.StringUtils;

import java.util.Date;


public class ConsultServiceValidator {
        public String checkDataCorrect(Long vetId, Long petId, Date date, String description){
            if (vetId == null || petId == null || date == null
                    || StringUtils.isNullOrEmpty(description)) {
                return "WRONG DATA, CORRECT INPUT !";
            }
            return null;
        }
}
