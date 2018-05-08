package org.tuiter.errors.validators;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tuiter.errors.annotations.ValidGender;
import org.tuiter.util.Gender;



public class GenderValidator implements ConstraintValidator<ValidGender, Gender>{
	private String[] values = {"M", "F", "UNKNOWN"};
	
	@Override
	public boolean isValid(Gender value, ConstraintValidatorContext context) {
		System.out.println("adsaçldasklasjkdlsaj " + value);
		return Arrays.asList(values).contains(value == null ? null : value.toString());
	}

}
