package org.corrige.ai.validations.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import org.corrige.ai.validations.validators.GenderValidator;

@NotNull(message = "Gender can not be null")

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = {GenderValidator.class})
@Documented
public @interface ValidGender {
	String message() default "Gender is not valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
