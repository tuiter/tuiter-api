package org.tuiter.errors.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NotBlank(message = "Name can not be blank")
@Size(max = 100, message = "Name can not have more than 100 characters")

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = {})
@Documented
public @interface ValidName {
	String message() default "Name is not valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
