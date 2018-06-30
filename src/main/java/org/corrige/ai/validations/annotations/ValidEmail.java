package org.corrige.ai.validations.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NotBlank(message = "Email can not be blank")
@Email(message = "Email should be valid")

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = {})
@Documented
public @interface ValidEmail {
	String message() default "Email is not valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
