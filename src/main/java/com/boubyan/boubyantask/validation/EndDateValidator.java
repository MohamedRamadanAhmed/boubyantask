package com.boubyan.boubyantask.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EndDateValidator implements ConstraintValidator<EndDate, LocalDate> {

    @Override
    public void initialize(EndDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null && value.isBefore(LocalDate.now());
    }
}