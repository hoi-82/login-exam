package com.example.login.loginexam.domain.enums.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomEnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
    private ValidEnum validEnum;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.validEnum = constraintAnnotation;
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        Object[] enumValues = this.validEnum.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value == enumValue) {
                    return true;
                }
            }
        }
        return false;
    }
}
