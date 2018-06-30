package org.corrige.ai.validations.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NotBlank(message = "Username can not be blank")
@Size(min = 4, max = 15, message = "Username can not have less than 4 characters and more than 15 characters")
@Pattern(regexp = "^(?!.*[-_]{2,})(?=^[^0-9])(?=^[^-_].*[^-_]$)[\\w\\s-]{0,}$", message = "Wrong username")

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = {})
@Documented
public @interface ValidUsername {
	String message() default "Username is not valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
