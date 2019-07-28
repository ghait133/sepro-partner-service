package com.sepro.partnerservice.validation;


import com.sepro.partnerservice.dto.PartnerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final PartnerDto partner = (PartnerDto) obj;
        return partner.getPassword().equals(partner.getMatchingPassword());
    }

}
