package workshopJudge_v2.validation.anotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import workshopJudge_v2.validation.validator.MatchPasswordValidator;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchPasswordValidator.class)
@Documented
public @interface MatchPasswords {

    String message() default "Passwords does not match";

    String originalField();

    String confirmField();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
