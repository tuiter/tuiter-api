package org.corrige.ai.errors.annotations;

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

@NotBlank(message = "Password can not be blank")
@Size(min = 6, message = "Password can not have less than 6 characters")
@Pattern(regexp = "^(?=.*[0-9]).{0,}$", message = "Password has no numbers")
@Pattern(regexp = "^(?=.*[a-zA-Z]).{0,}$", message = "Password has no letters")

@Retention(RUNTIME)
@Target({ METHOD, FIELD })
@Constraint(validatedBy = {})
@Documented
public @interface ValidPassword {
	String message() default "Password is not valid!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
