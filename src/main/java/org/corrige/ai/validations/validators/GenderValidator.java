package org.corrige.ai.validations.validators;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.corrige.ai.enums.Gender;
import org.corrige.ai.validations.annotations.ValidGender;



public class GenderValidator implements ConstraintValidator<ValidGender, Gender>{
	private String[] values = {"M", "F", "UNKNOWN"};
	
	@Override
	public boolean isValid(Gender value, ConstraintValidatorContext context) {
		return Arrays.asList(values).contains(value == null ? null : value.toString());
	}

}
